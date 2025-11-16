package net.pixeldreamstudios.gearsofvalor.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorClientMod;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;



public final class GearsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GearsOfValorMod.initClient();
        GearsOfValorClientMod.initClientAzRenders();
    }
}
