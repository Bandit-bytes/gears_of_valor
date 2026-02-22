package net.bandit.gears_of_valor.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorClientMod;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public final class GearsModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GearsOfValorMod.initClient();
        GearsOfValorClientMod.initClientAzRenders();
    }
}
