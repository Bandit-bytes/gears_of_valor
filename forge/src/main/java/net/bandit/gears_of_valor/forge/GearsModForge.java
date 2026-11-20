package net.bandit.gears_of_valor.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

@Mod(GearsOfValorMod.MOD_ID)
public final class GearsModForge {
    public GearsModForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(GearsOfValorMod.MOD_ID, modEventBus);
        GearsOfValorMod.init();
        modEventBus.addListener(GearsModForge::onCommonSetup);
    }

    // Runs on both client and dedicated server. (Previously the identity registry was in a static
    // @SubscribeEvent method on an instance-registered class, so it never ran.)
    private static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(GearsOfValorMod::initAzIdentityRegistry);
    }
}
