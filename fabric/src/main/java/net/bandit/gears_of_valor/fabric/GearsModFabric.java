package net.bandit.gears_of_valor.fabric;

import net.fabricmc.api.ModInitializer;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public final class GearsModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GearsOfValorMod.init();
        GearsOfValorMod.initAzIdentityRegistry();

        FabricWorldgen.init();
    }
}
