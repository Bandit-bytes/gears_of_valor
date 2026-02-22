package net.pixeldreamstudios.gearsofvalor.item.weapons.renderer;


import mod.azure.azurelib.render.item.AzItemRenderer;
import mod.azure.azurelib.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.weapons.animator.HarbringerAnimator;

public class FuryRenderer extends AzItemRenderer {
    private static final ResourceLocation GEO = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "geo/weapon/furys_call.geo.json"
    );

    private static final ResourceLocation TEX = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "textures/weapon/furys_call.png"
    );

    public FuryRenderer() {
        super(
                AzItemRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(HarbringerAnimator::new)
                        .useEntityGuiLighting()
                        .useNewOffset(false)
                        .build()
        );
    }
}
