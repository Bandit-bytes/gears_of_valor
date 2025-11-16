package net.pixeldreamstudios.gearsofvalor.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorClientMod;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

@EventBusSubscriber(modid = GearsOfValorMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GearsOfValorNeoForgeClient {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        GearsOfValorClientMod.initClientAzRenders();
    }
}
