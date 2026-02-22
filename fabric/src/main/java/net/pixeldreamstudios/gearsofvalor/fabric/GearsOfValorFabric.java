package net.pixeldreamstudios.gearsofvalor.fabric;

import net.fabricmc.api.ModInitializer;

import net.pixeldreamstudios.gearsofvalor.GearsOfValorClientMod;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public final class GearsOfValorFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GearsOfValorMod.init();
        GearsOfValorMod.initAzIdentityRegistry();

        FabricWorldgen.init();
    }
}
