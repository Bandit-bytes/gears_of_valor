package net.pixeldreamstudios.gearsofvalor;

import mod.azure.azurelib.common.internal.common.AzureLib;
import net.pixeldreamstudios.gearsofvalor.registry.ArmorRegistering;
import net.pixeldreamstudios.gearsofvalor.registry.ArmorRegistry;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;
import net.pixeldreamstudios.gearsofvalor.registry.TabRegistry;

public final class GearsOfValorMod {
    public static final String MOD_ID = "gears_of_valor";

    public static void init() {

        AzureLib.initialize();
        TabRegistry.init();
        ItemRegistry.register();
        ArmorRegistry.init();

        ArmorRegistering.init(MOD_ID);
    }
}
