package net.pixeldreamstudios.gearsofvalor.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.registry.ArmorRegistering;

import static net.pixeldreamstudios.gearsofvalor.GearsOfValorMod.MOD_ID;

@Mod(MOD_ID)
public final class GearsOfValorNeo {
    public GearsOfValorNeo(IEventBus modEventBus) {
        GearsOfValorMod.init();
        modEventBus.addListener(this::onClientSetup);
    }
    private void onClientSetup(final FMLClientSetupEvent event) {
        ArmorRegistering.initClient(MOD_ID);
    }
}
