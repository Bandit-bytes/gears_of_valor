package net.pixeldreamstudios.gearsofvalor;

import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;
import mod.azure.azurelib.common.render.item.AzItemRendererRegistry;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.ArismasArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.PaladinArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.ReaperArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.WoodlandArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.FuryRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.HarbringerRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.HeavensRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.ShadowRenderer;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

public class GearsOfValorClientMod {

    public static void initClientAzRenders() {
        AzItemRendererRegistry.register(ItemRegistry.FURYS_CALL.get(), FuryRenderer::new);
        AzItemRendererRegistry.register(ItemRegistry.HARBRINGER.get(), HarbringerRenderer::new);
        AzItemRendererRegistry.register(ItemRegistry.HEAVENS_FALL.get(), HeavensRenderer::new);
        AzItemRendererRegistry.register(ItemRegistry.SHADOW_STALKER.get(), ShadowRenderer::new);
        AzArmorRendererRegistry.register(
                PaladinArmorRenderer::new,
                ItemRegistry.PALADIN_HELMET.get(),
                ItemRegistry.PALADIN_CHESTPLATE.get(),
                ItemRegistry.PALADIN_LEGGINGS.get(),
                ItemRegistry.PALADIN_BOOTS.get()
        );
        AzArmorRendererRegistry.register(
                ArismasArmorRenderer::new,
                ItemRegistry.ARISMAS_AWAKENING_HELMET.get(),
                ItemRegistry.ARISMAS_AWAKENING_CHESTPLATE.get(),
                ItemRegistry.ARISMAS_AWAKENING_LEGGINGS.get(),
                ItemRegistry.ARISMAS_AWAKENING_BOOTS.get()
        );
        AzArmorRendererRegistry.register(
                ReaperArmorRenderer::new,
                ItemRegistry.REAPERS_EMBRACE_HELMET.get(),
                ItemRegistry.REAPERS_EMBRACE_CHESTPLATE.get(),
                ItemRegistry.REAPERS_EMBRACE_LEGGINGS.get(),
                ItemRegistry.REAPERS_EMBRACE_BOOTS.get()
        );
        AzArmorRendererRegistry.register(
                WoodlandArmorRenderer::new,
                ItemRegistry.WOODLAND_SPIRIT_HELMET.get(),
                ItemRegistry.WOODLAND_SPIRIT_CHESTPLATE.get(),
                ItemRegistry.WOODLAND_SPIRIT_LEGGINGS.get(),
                ItemRegistry.WOODLAND_SPIRIT_BOOTS.get()
        );
    }

}
