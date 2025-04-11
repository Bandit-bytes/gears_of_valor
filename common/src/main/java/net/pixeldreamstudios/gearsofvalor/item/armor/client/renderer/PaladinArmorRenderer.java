package net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.animator.PaladinAnimator;

public class PaladinArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "geo/armor/paladin.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "textures/armor/paladin.png"
    );

    public PaladinArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(PaladinAnimator::new)
                        .build()
        );
    }
}
