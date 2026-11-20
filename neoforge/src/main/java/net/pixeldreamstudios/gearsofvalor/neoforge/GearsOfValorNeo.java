package net.pixeldreamstudios.gearsofvalor.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

import static net.pixeldreamstudios.gearsofvalor.GearsOfValorMod.MOD_ID;

@Mod(MOD_ID)
public final class GearsOfValorNeo {
    public GearsOfValorNeo(IEventBus modEventBus) {
        GearsOfValorMod.init();
        modEventBus.addListener(GearsOfValorNeo::onCommonSetup);
    }

    // Runs on both client and dedicated server (previously this was only done in a client-only model event).
    private static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(GearsOfValorMod::initAzIdentityRegistry);
    }
}
