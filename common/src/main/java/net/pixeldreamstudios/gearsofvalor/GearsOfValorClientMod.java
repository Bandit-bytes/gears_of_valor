package net.pixeldreamstudios.gearsofvalor;

import mod.azure.azurelib.render.armor.AzArmorRendererRegistry;
import mod.azure.azurelib.render.item.AzItemRendererRegistry;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.GearsArmorRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.client.GearsWeaponRenderer;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

public final class GearsOfValorClientMod {
    private GearsOfValorClientMod() {}

    /** Registers the AzureLib renderers. Call exactly once, on the client, after items are registered. */
    public static void initClientAzRenders() {
        AzItemRendererRegistry.register(ItemRegistry.FURYS_CALL.get(), () -> new GearsWeaponRenderer("furys_call"));
        AzItemRendererRegistry.register(ItemRegistry.HARBRINGER.get(), () -> new GearsWeaponRenderer("harbringer"));
        AzItemRendererRegistry.register(ItemRegistry.HEAVENS_FALL.get(), () -> new GearsWeaponRenderer("heavens_fall"));
        AzItemRendererRegistry.register(ItemRegistry.SHADOW_STALKER.get(), () -> new GearsWeaponRenderer("shadow_stalker"));

        AzArmorRendererRegistry.register(() -> new GearsArmorRenderer("paladin"),
                ItemRegistry.PALADIN_HELMET.get(),
                ItemRegistry.PALADIN_CHESTPLATE.get(),
                ItemRegistry.PALADIN_LEGGINGS.get(),
                ItemRegistry.PALADIN_BOOTS.get());
        AzArmorRendererRegistry.register(() -> new GearsArmorRenderer("arismas_awakening"),
                ItemRegistry.ARISMAS_AWAKENING_HELMET.get(),
                ItemRegistry.ARISMAS_AWAKENING_CHESTPLATE.get(),
                ItemRegistry.ARISMAS_AWAKENING_LEGGINGS.get(),
                ItemRegistry.ARISMAS_AWAKENING_BOOTS.get());
        AzArmorRendererRegistry.register(() -> new GearsArmorRenderer("reapers_embrace"),
                ItemRegistry.REAPERS_EMBRACE_HELMET.get(),
                ItemRegistry.REAPERS_EMBRACE_CHESTPLATE.get(),
                ItemRegistry.REAPERS_EMBRACE_LEGGINGS.get(),
                ItemRegistry.REAPERS_EMBRACE_BOOTS.get());
        AzArmorRendererRegistry.register(() -> new GearsArmorRenderer("woodland_spirit"),
                ItemRegistry.WOODLAND_SPIRIT_HELMET.get(),
                ItemRegistry.WOODLAND_SPIRIT_CHESTPLATE.get(),
                ItemRegistry.WOODLAND_SPIRIT_LEGGINGS.get(),
                ItemRegistry.WOODLAND_SPIRIT_BOOTS.get());
    }
}
