package net.pixeldreamstudios.gearsofvalor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> FALLEN_CORE = ITEMS.register("fallen_core",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> FALLEN = ITEMS.register("fallen",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> UNSEEN_SOUL = ITEMS.register("unseen_soul",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> ARISMA_SCALE = ITEMS.register("arisma_scale",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> NOBLE_GEM = ITEMS.register("noble_gem",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> RAW_FALLEN = ITEMS.register("raw_fallen",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> RAW_SOULINITE = ITEMS.register("raw_soulinite",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> SOULINITE = ITEMS.register("soulinite",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> TAINTED_IRON = ITEMS.register("tainted_iron",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.UNCOMMON)));



    public static void register() {
        ITEMS.register();
    }
}
