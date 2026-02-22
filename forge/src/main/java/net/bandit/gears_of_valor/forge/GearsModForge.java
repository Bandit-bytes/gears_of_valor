package net.bandit.gears_of_valor.forge;

import dev.architectury.platform.forge.EventBuses;
import mod.azure.azurelib.animation.cache.AzIdentityRegistry;
import mod.azure.azurelib.render.armor.AzArmorRendererRegistry;
import mod.azure.azurelib.render.item.AzItemRendererRegistry;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

@Mod(GearsOfValorMod.MOD_ID)
public final class GearsModForge {
    public GearsModForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(GearsOfValorMod.MOD_ID, modEventBus);
        modEventBus.register(this);
        modEventBus.addListener(this::onClientSetup);
        GearsOfValorMod.init();
    }
    private void onClientSetup(final FMLClientSetupEvent event) {
        GearsOfValorMod.initClient();
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
