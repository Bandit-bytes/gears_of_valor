package net.bandit.gears_of_valor.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorClientMod;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

@Mod.EventBusSubscriber(modid = GearsOfValorMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GearsModClientForge {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        GearsOfValorMod.initClient();
        GearsOfValorClientMod.initClientAzRenders();
    }
}
