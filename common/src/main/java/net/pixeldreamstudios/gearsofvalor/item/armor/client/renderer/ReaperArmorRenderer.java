package net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.animator.PaladinAnimator;

public class ReaperArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "geo/armor/reapers_embrace.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "textures/armor/reapers_embrace.png"
    );

    public ReaperArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(PaladinAnimator::new)
                        .build()
        );
    }
}
