package net.pixeldreamstudios.gearsofvalor.item.weapons.renderer;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.weapons.animator.HarbringerAnimator;

public class ShadowRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "geo/weapon/shadow_stalker.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "textures/weapon/shadow_stalker.png"
    );

    public ShadowRenderer() {
        super(
                AzItemRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(HarbringerAnimator::new)
                        .useEntityGuiLighting()
                        .useNewOffset(false)
                        .build()
        );
    }
}
