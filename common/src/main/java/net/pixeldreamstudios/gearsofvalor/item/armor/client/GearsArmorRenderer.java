package net.pixeldreamstudios.gearsofvalor.item.armor.client;

import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.render.AzRendererPipelineContext;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.armor.AzArmorRendererPipelineContext;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.client.GearsItemAnimator;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Renderer shared by every armor set. Expects {@code geo/armor/<name>.geo.json}, {@code textures/armor/<name>.png}
 * and {@code animations/armor/<name>.animation.json}.
 */
public class GearsArmorRenderer extends AzArmorRenderer {
    /**
     * Uniform scale applied to the {@code helmet} bone around the centre of the head. Sized so the helmet shell sits
     * outside the 3D hat layer drawn by mods such as 3D Skin Layers (the shells reach ~5.3px from the head centre;
     * vanilla helmets reach 5px).
     */
    public static final float HELMET_SCALE = 1.17F;

    public GearsArmorRenderer(String name) {
        super(AzArmorRendererConfig.builder(
                        GearsOfValorMod.modResource("geo/armor/" + name + ".geo.json"),
                        GearsOfValorMod.modResource("textures/armor/" + name + ".png"))
                .setAnimatorProvider(() -> new GearsItemAnimator("armor/", name))
                .setPrerenderEntry(GearsArmorRenderer::preRender)
                .build());
    }

    /** Runs after AzureLib has copied the vanilla model's pose onto the armor bones for this frame. */
    private static AzRendererPipelineContext<UUID, ItemStack> preRender(AzRendererPipelineContext<UUID, ItemStack> context) {
        if (context instanceof AzArmorRendererPipelineContext armorContext) {
            var bones = armorContext.boneContext();
            AzBone helmet = child(bones.head, "helmet");
            if (helmet != null) {
                helmet.updateScale(HELMET_SCALE, HELMET_SCALE, HELMET_SCALE);
            }
            AzBone cape = child(bones.body, "cape");
            if (cape != null) {
                poseCape(cape, armorContext.currentEntity(), context.partialTick());
            }
        }
        return context;
    }

    private static @Nullable AzBone child(@Nullable AzBone parent, String name) {
        if (parent == null) return null;
        for (AzBone bone : parent.getChildBones()) {
            if (bone.getName().equals(name)) return bone;
        }
        return null;
    }

    /** Vanilla-style cape physics, driven by the entity actually wearing the armor. */
    private static void poseCape(AzBone cape, @Nullable Entity entity, float partialTick) {
        float rotX = 0.1F;
        float rotZ = 0.0F;

        if (entity instanceof Player player) {
            double dx = Mth.lerp(partialTick, player.xCloakO, player.xCloak) - Mth.lerp(partialTick, player.xo, player.getX());
            double dy = Mth.lerp(partialTick, player.yCloakO, player.yCloak) - Mth.lerp(partialTick, player.yo, player.getY());
            double dz = Mth.lerp(partialTick, player.zCloakO, player.zCloak) - Mth.lerp(partialTick, player.zo, player.getZ());

            float bodyRot = Mth.rotLerp(partialTick, player.yBodyRotO, player.yBodyRot) * Mth.DEG_TO_RAD;
            double sin = Mth.sin(bodyRot);
            double cos = -Mth.cos(bodyRot);

            float lift = Mth.clamp((float) dy * 10.0F, -6.0F, 32.0F);
            float swayX = Mth.clamp((float) (dx * sin + dz * cos) * 100.0F, 0.0F, 150.0F);
            float swayZ = Mth.clamp((float) (dx * cos - dz * sin) * 100.0F, -20.0F, 20.0F);

            float bob = Mth.lerp(partialTick, player.oBob, player.bob);
            lift += Mth.sin(Mth.lerp(partialTick, player.walkDistO, player.walkDist) * 6.0F) * 8.0F * bob;
            if (player.isCrouching()) lift += 10.0F;

            rotX -= swayX / 200.0F + lift / 40.0F;
            rotZ += swayZ / 180.0F;
        }

        cape.updateRotation(rotX, 0.0F, rotZ);
    }
}
