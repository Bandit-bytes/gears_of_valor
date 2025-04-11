package net.pixeldreamstudios.gearsofvalor.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> GEARS_TAB = TABS.register(
            GearsOfValorMod.MOD_ID + "_tab",
            () -> CreativeTabRegistry.create(
                    Component.translatable("category." + GearsOfValorMod.MOD_ID),
                    () -> new ItemStack(ArmorRegistering.getItem(GearsOfValorMod.MOD_ID, "paladin_helmet"))
            )
    );

    public static void init() {
        TABS.register();
    }
}
