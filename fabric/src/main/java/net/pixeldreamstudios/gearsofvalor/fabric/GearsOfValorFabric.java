package net.pixeldreamstudios.gearsofvalor.fabric;

import mod.azure.azurelib.rewrite.animation.cache.AzIdentityRegistry;
import net.fabricmc.api.ModInitializer;

import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

public final class GearsOfValorFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GearsOfValorMod.init();
        AzIdentityRegistry.register(
                ItemRegistry.FURYS_CALL.get(),
                ItemRegistry.SHADOW_STALKER.get(),
                ItemRegistry.HARBRINGER.get(),
                ItemRegistry.HEAVENS_FALL.get(),
                ItemRegistry.REAPERS_EMBRACE_HELMET.get(),
                ItemRegistry.REAPERS_EMBRACE_CHESTPLATE.get(),
                ItemRegistry.REAPERS_EMBRACE_LEGGINGS.get(),
                ItemRegistry.REAPERS_EMBRACE_BOOTS.get(),
                ItemRegistry.ARISMAS_AWAKENING_HELMET.get(),
                ItemRegistry.ARISMAS_AWAKENING_CHESTPLATE.get(),
                ItemRegistry.ARISMAS_AWAKENING_LEGGINGS.get(),
                ItemRegistry.ARISMAS_AWAKENING_BOOTS.get(),
                ItemRegistry.PALADIN_HELMET.get(),
                ItemRegistry.PALADIN_CHESTPLATE.get(),
                ItemRegistry.PALADIN_LEGGINGS.get(),
                ItemRegistry.PALADIN_BOOTS.get(),
                ItemRegistry.WOODLAND_SPIRIT_HELMET.get(),
                ItemRegistry.WOODLAND_SPIRIT_CHESTPLATE.get(),
                ItemRegistry.WOODLAND_SPIRIT_LEGGINGS.get(),
                ItemRegistry.WOODLAND_SPIRIT_BOOTS.get()
        );
    }
}
