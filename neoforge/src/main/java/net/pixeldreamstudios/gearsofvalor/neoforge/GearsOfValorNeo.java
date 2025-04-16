package net.pixeldreamstudios.gearsofvalor.neoforge;

import mod.azure.azurelib.rewrite.animation.cache.AzIdentityRegistry;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererRegistry;
import mod.azure.azurelib.rewrite.render.item.AzItemRendererRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.ArismasArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.PaladinArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.ReaperArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.WoodlandArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.FuryRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.HarbringerRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.HeavensRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.ShadowRenderer;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

import static net.pixeldreamstudios.gearsofvalor.GearsOfValorMod.MOD_ID;


@Mod(MOD_ID)
@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class GearsOfValorNeo {
    public GearsOfValorNeo(IEventBus modEventBus) {
        GearsOfValorMod.init();
        modEventBus.addListener(this::onClientSetup);
    }
    private void onClientSetup(final FMLClientSetupEvent event) {
    }
    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional event) {
        AzIdentityRegistry.register(
                ItemRegistry.FURYS_CALL.get(),
                ItemRegistry.SHADOW_STALKER.get(),
                ItemRegistry.HARBRINGER.get(),
                ItemRegistry.HEAVENS_FALL.get(),
                ItemRegistry.REAPERS_EMBRACE_HELMET.get(),
                ItemRegistry.REAPERS_EMBRACE_CHESTPLATE.get(),
                ItemRegistry.REAPERS_EMBRACE_LEGGINGS.get(),
                ItemRegistry.REAPERS_EMBRACE_BOOTS.get(),
                ItemRegistry.ARISMAS_AWAKENING_HELMET.get(),
                ItemRegistry.ARISMAS_AWAKENING_CHESTPLATE.get(),
                ItemRegistry.ARISMAS_AWAKENING_LEGGINGS.get(),
                ItemRegistry.ARISMAS_AWAKENING_BOOTS.get(),
                ItemRegistry.PALADIN_HELMET.get(),
                ItemRegistry.PALADIN_CHESTPLATE.get(),
                ItemRegistry.PALADIN_LEGGINGS.get(),
                ItemRegistry.PALADIN_BOOTS.get(),
                ItemRegistry.WOODLAND_SPIRIT_HELMET.get(),
                ItemRegistry.WOODLAND_SPIRIT_CHESTPLATE.get(),
                ItemRegistry.WOODLAND_SPIRIT_LEGGINGS.get(),
                ItemRegistry.WOODLAND_SPIRIT_BOOTS.get()
        );
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        AzItemRendererRegistry.register(ItemRegistry.FURYS_CALL.get(), FuryRenderer::new);
        AzItemRendererRegistry.register(ItemRegistry.HARBRINGER.get(), HarbringerRenderer::new);
        AzItemRendererRegistry.register(ItemRegistry.HEAVENS_FALL.get(), HeavensRenderer::new);
        AzItemRendererRegistry.register(ItemRegistry.SHADOW_STALKER.get(), ShadowRenderer::new);
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
    }
}
