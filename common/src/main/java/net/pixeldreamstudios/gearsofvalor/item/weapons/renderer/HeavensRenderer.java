package net.pixeldreamstudios.gearsofvalor.item.weapons.renderer;

import mod.azure.azurelib.rewrite.render.item.AzItemRenderer;
import mod.azure.azurelib.rewrite.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.weapons.animator.HarbringerAnimator;

public class HeavensRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "geo/weapon/heavens_fall.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "textures/weapon/heavens_fall.png"
    );

    public HeavensRenderer() {
        super(
                AzItemRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(HarbringerAnimator::new)
                        .useEntityGuiLighting()
                        .useNewOffset(false)
                        .build()
        );
    }
}
