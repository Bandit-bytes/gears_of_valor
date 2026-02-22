package net.pixeldreamstudios.gearsofvalor.item.weapons.renderer;

import mod.azure.azurelib.render.item.AzItemRenderer;
import mod.azure.azurelib.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.weapons.animator.HarbringerAnimator;

public class HarbringerRenderer  extends AzItemRenderer {
    private static final ResourceLocation GEO = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "geo/weapon/harbringer.geo.json"
    );

    private static final ResourceLocation TEX = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "textures/weapon/harbringer.png"
    );

    public HarbringerRenderer() {
        super(
                AzItemRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(HarbringerAnimator::new)
                        .useEntityGuiLighting()
                        .useNewOffset(false)
                        .build()
        );
    }
}
