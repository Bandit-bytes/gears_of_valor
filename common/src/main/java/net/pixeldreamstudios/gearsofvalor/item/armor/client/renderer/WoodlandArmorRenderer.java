package net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.animator.PaladinAnimator;

public class WoodlandArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "geo/armor/woodland_spirit.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "textures/armor/woodland_spirit.png"
    );

    public WoodlandArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(PaladinAnimator::new)
                        .build()
        );
    }
}
