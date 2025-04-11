package net.pixeldreamstudios.gearsofvalor.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.pixeldreamstudios.gearsofvalor.registry.ArmorRegistering;

import static net.pixeldreamstudios.gearsofvalor.GearsOfValorMod.MOD_ID;

public final class GearsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ArmorRegistering.initClient(MOD_ID);
    }
}
