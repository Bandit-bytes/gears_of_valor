package net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer;


import mod.azure.azurelib.render.armor.AzArmorRenderer;
import mod.azure.azurelib.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.animator.WoodlandAnimator;

public class WoodlandArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "geo/armor/woodland_spirit.geo.json"
    );

    private static final ResourceLocation TEX = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "textures/armor/woodland_spirit.png"
    );

    public WoodlandArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(WoodlandAnimator::new)
                        .build()
        );
    }
}
