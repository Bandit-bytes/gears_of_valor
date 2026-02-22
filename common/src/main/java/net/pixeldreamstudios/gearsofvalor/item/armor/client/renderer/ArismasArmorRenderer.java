package net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer;


import mod.azure.azurelib.render.armor.AzArmorRenderer;
import mod.azure.azurelib.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.animator.ArismaAnimator;

public class ArismasArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "geo/armor/arismas_awakening.geo.json"
    );

    private static final ResourceLocation TEX = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "textures/armor/arismas_awakening.png"
    );

    public ArismasArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(ArismaAnimator::new)
                        .build()
        );
    }
}
