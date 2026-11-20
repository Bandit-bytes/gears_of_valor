package net.pixeldreamstudios.gearsofvalor.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

@EventBusSubscriber(modid = GearsOfValorMod.MOD_ID, value = Dist.CLIENT)
public final class GearsOfValorNeoForgeClient {
    private GearsOfValorNeoForgeClient() {}

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(GearsOfValorMod::initClient);
    }
}
