package net.pixeldreamstudios.gearsofvalor;

import mod.azure.azurelib.common.animation.cache.AzIdentityRegistry;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.gearsofvalor.events.RitualEventHandler;
import net.pixeldreamstudios.gearsofvalor.events.WitherSoulDropHandler;
import net.pixeldreamstudios.gearsofvalor.registry.*;
import net.pixeldreamstudios.gearsofvalor.villagers.GearsVillagerTrades;

public final class GearsOfValorMod {
    public static final String MOD_ID = "gears_of_valor";

    public static void init() {

        TabRegistry.init();
        ItemRegistry.register();
        BlockRegistry.init();
        ComponentRegistry.register();
        RitualEventHandler.init();
        GearsVillagerTrades.init();
        WitherSoulDropHandler.init();
    }

    public static void initAzIdentityRegistry() {
        AzIdentityRegistry.register(
                ItemRegistry.FURYS_CALL.get(),
                ItemRegistry.HARBRINGER.get(),
                ItemRegistry.HEAVENS_FALL.get(),
                ItemRegistry.SHADOW_STALKER.get(),
                ItemRegistry.WOODLAND_SPIRIT_CHESTPLATE.get(),
                ItemRegistry.WOODLAND_SPIRIT_LEGGINGS.get(),
                ItemRegistry.WOODLAND_SPIRIT_BOOTS.get(),
                ItemRegistry.WOODLAND_SPIRIT_HELMET.get(),
                ItemRegistry.PALADIN_LEGGINGS.get(),
                ItemRegistry.PALADIN_CHESTPLATE.get(),
                ItemRegistry.PALADIN_HELMET.get(),
                ItemRegistry.ARISMAS_AWAKENING_CHESTPLATE.get(),
                ItemRegistry.ARISMAS_AWAKENING_LEGGINGS.get(),
                ItemRegistry.ARISMAS_AWAKENING_BOOTS.get(),
                ItemRegistry.ARISMAS_AWAKENING_HELMET.get(),
                ItemRegistry.REAPERS_EMBRACE_BOOTS.get(),
                ItemRegistry.REAPERS_EMBRACE_LEGGINGS.get(),
                ItemRegistry.REAPERS_EMBRACE_CHESTPLATE.get(),
                ItemRegistry.REAPERS_EMBRACE_HELMET.get(),
                ItemRegistry.PALADIN_BOOTS.get()
        );
    }

    public static ResourceLocation modResource(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static void initClient() {
        GearsOfValorClientMod.initClientAzRenders();
    }
}
