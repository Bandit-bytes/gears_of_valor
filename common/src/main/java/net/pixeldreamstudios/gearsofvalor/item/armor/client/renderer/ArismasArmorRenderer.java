package net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.animator.PaladinAnimator;

public class ArismasArmorRenderer extends AzArmorRenderer {
    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "geo/armor/arismas_awakening.geo.json"
    );

    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            GearsOfValorMod.MOD_ID,
            "textures/armor/arismas_awakening.png"
    );

    public ArismasArmorRenderer() {
        super(
                AzArmorRendererConfig.builder(GEO, TEX)
                        .setAnimatorProvider(PaladinAnimator::new)
                        .build()
        );
    }
}
