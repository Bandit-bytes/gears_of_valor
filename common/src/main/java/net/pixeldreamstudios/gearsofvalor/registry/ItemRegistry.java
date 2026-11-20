package net.pixeldreamstudios.gearsofvalor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.ArmorSet;
import net.pixeldreamstudios.gearsofvalor.item.armor.GearsArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.items.CursedStoneItem;
import net.pixeldreamstudios.gearsofvalor.item.items.PureStoneItem;
import net.pixeldreamstudios.gearsofvalor.item.items.UnseenSoulItem;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.FurySword;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.HarbringerSword;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.HeavensSword;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.ShadowSword;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> FALLEN_CORE = ITEMS.register("fallen_core",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> CURSED_MOONSHARD = ITEMS.register("cursed_moonshard",
            () -> new CursedStoneItem(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> PURE_STONE = ITEMS.register("purification_stone",
            () -> new PureStoneItem(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> FALLEN = ITEMS.register("fallen",
            () -> new Item(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.RARE)));

    public static final RegistrySupplier<Item> UNSEEN_SOUL = ITEMS.register("unseen_soul",
            () -> new UnseenSoulItem(new Item.Properties().arch$tab(TabRegistry.GEARS_TAB).rarity(Rarity.RARE)));

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

    // Paladin Armor
    public static final RegistrySupplier<Item> PALADIN_HELMET = armor("paladin_helmet", ArmorSet.PALADIN, ArmorItem.Type.HELMET);
    public static final RegistrySupplier<Item> PALADIN_CHESTPLATE = armor("paladin_chestplate", ArmorSet.PALADIN, ArmorItem.Type.CHESTPLATE);
    public static final RegistrySupplier<Item> PALADIN_LEGGINGS = armor("paladin_leggings", ArmorSet.PALADIN, ArmorItem.Type.LEGGINGS);
    public static final RegistrySupplier<Item> PALADIN_BOOTS = armor("paladin_boots", ArmorSet.PALADIN, ArmorItem.Type.BOOTS);

    // Reapers Embrace Armor
    public static final RegistrySupplier<Item> REAPERS_EMBRACE_HELMET = armor("reapers_embrace_helmet", ArmorSet.REAPER, ArmorItem.Type.HELMET);
    public static final RegistrySupplier<Item> REAPERS_EMBRACE_CHESTPLATE = armor("reapers_embrace_chestplate", ArmorSet.REAPER, ArmorItem.Type.CHESTPLATE);
    public static final RegistrySupplier<Item> REAPERS_EMBRACE_LEGGINGS = armor("reapers_embrace_leggings", ArmorSet.REAPER, ArmorItem.Type.LEGGINGS);
    public static final RegistrySupplier<Item> REAPERS_EMBRACE_BOOTS = armor("reapers_embrace_boots", ArmorSet.REAPER, ArmorItem.Type.BOOTS);

    // Woodland Spirit Armor
    public static final RegistrySupplier<Item> WOODLAND_SPIRIT_HELMET = armor("woodland_spirit_helmet", ArmorSet.WOODLAND, ArmorItem.Type.HELMET);
    public static final RegistrySupplier<Item> WOODLAND_SPIRIT_CHESTPLATE = armor("woodland_spirit_chestplate", ArmorSet.WOODLAND, ArmorItem.Type.CHESTPLATE);
    public static final RegistrySupplier<Item> WOODLAND_SPIRIT_LEGGINGS = armor("woodland_spirit_leggings", ArmorSet.WOODLAND, ArmorItem.Type.LEGGINGS);
    public static final RegistrySupplier<Item> WOODLAND_SPIRIT_BOOTS = armor("woodland_spirit_boots", ArmorSet.WOODLAND, ArmorItem.Type.BOOTS);

    // Arismas Awakening Armor
    public static final RegistrySupplier<Item> ARISMAS_AWAKENING_HELMET = armor("arismas_awakening_helmet", ArmorSet.ARISMAS, ArmorItem.Type.HELMET);
    public static final RegistrySupplier<Item> ARISMAS_AWAKENING_CHESTPLATE = armor("arismas_awakening_chestplate", ArmorSet.ARISMAS, ArmorItem.Type.CHESTPLATE);
    public static final RegistrySupplier<Item> ARISMAS_AWAKENING_LEGGINGS = armor("arismas_awakening_leggings", ArmorSet.ARISMAS, ArmorItem.Type.LEGGINGS);
    public static final RegistrySupplier<Item> ARISMAS_AWAKENING_BOOTS = armor("arismas_awakening_boots", ArmorSet.ARISMAS, ArmorItem.Type.BOOTS);

    // Weapons
    public static final RegistrySupplier<Item> HARBRINGER = ITEMS.register("harbringer", () -> new HarbringerSword(weaponProps()));
    public static final RegistrySupplier<Item> FURYS_CALL = ITEMS.register("furys_call", () -> new FurySword(weaponProps()));
    public static final RegistrySupplier<Item> HEAVENS_FALL = ITEMS.register("heavens_fall", () -> new HeavensSword(weaponProps()));
    public static final RegistrySupplier<Item> SHADOW_STALKER = ITEMS.register("shadow_stalker", () -> new ShadowSword(weaponProps()));

    private static RegistrySupplier<Item> armor(String name, ArmorSet set, ArmorItem.Type type) {
        return ITEMS.register(name, () -> new GearsArmorItem(set, type, new Item.Properties()
                .durability(type.getDurability(durabilityMultiplier(set)))
                .rarity(set == ArmorSet.WOODLAND ? Rarity.RARE : Rarity.EPIC)
                .arch$tab(TabRegistry.GEARS_TAB)));
    }

    /** Same scale as vanilla (iron 15, diamond 33, netherite 37). */
    private static int durabilityMultiplier(ArmorSet set) {
        return switch (set) {
            case WOODLAND -> 25;
            case ARISMAS -> 30;
            case PALADIN -> 33;
            case REAPER -> 36;
        };
    }

    private static Item.Properties weaponProps() {
        return new Item.Properties().durability(2032).rarity(Rarity.RARE).arch$tab(TabRegistry.GEARS_TAB);
    }

    public static void register() {
        ITEMS.register();
    }
}
