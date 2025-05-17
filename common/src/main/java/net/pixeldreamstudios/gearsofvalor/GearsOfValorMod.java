package net.pixeldreamstudios.gearsofvalor;

import mod.azure.azurelib.common.internal.common.AzureLib;
import net.pixeldreamstudios.gearsofvalor.events.RitualEventHandler;
import net.pixeldreamstudios.gearsofvalor.events.WitherSoulDropHandler;
import net.pixeldreamstudios.gearsofvalor.registry.*;
import net.pixeldreamstudios.gearsofvalor.villagers.GearsVillagerTrades;

public final class GearsOfValorMod {
    public static final String MOD_ID = "gears_of_valor";

    public static void init() {

        AzureLib.initialize();
        TabRegistry.init();
        ItemRegistry.register();
        BlockRegistry.init();
        ComponentRegistry.register();
        RitualEventHandler.init();
        GearsVillagerTrades.init();
        WitherSoulDropHandler.init();
    }
}
