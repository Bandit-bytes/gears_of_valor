package net.pixeldreamstudios.gearsofvalor.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.ArismasArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.PaladinArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.ReaperArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.WoodlandArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.items.CursedStoneItem;
import net.pixeldreamstudios.gearsofvalor.item.items.PureStoneItem;
import net.pixeldreamstudios.gearsofvalor.item.items.UnseenSoulItem;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.FurySword;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.HarbringerSword;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.HeavensSword;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.ShadowSword;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GearsOfValorMod.MOD_ID, Registries.ITEM);
    static Item.Properties PaladinProps  = new Item.Properties().rarity(Rarity.EPIC).arch$tab(TabRegistry.GEARS_TAB);
    static Item.Properties WoodlandProps = new Item.Properties().rarity(Rarity.RARE).arch$tab(TabRegistry.GEARS_TAB);
    static Item.Properties ReaperProps   = new Item.Properties().rarity(Rarity.EPIC).arch$tab(TabRegistry.GEARS_TAB);

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
        public static final RegistrySupplier<Item> PALADIN_HELMET = ITEMS.register("paladin_helmet", () -> new PaladinArmorItem(ArmorItem.Type.HELMET, PaladinProps));
        public static final RegistrySupplier<Item> PALADIN_CHESTPLATE = ITEMS.register("paladin_chestplate", () -> new PaladinArmorItem(ArmorItem.Type.CHESTPLATE, PaladinProps));
        public static final RegistrySupplier<Item> PALADIN_LEGGINGS = ITEMS.register("paladin_leggings", () -> new PaladinArmorItem(ArmorItem.Type.LEGGINGS, PaladinProps));
        public static final RegistrySupplier<Item> PALADIN_BOOTS = ITEMS.register("paladin_boots", () -> new PaladinArmorItem(ArmorItem.Type.BOOTS, PaladinProps));

    // Reapers Embrace Armor
        public static final RegistrySupplier<Item> REAPERS_EMBRACE_HELMET = ITEMS.register("reapers_embrace_helmet", () -> new ReaperArmorItem(ArmorItem.Type.HELMET, WoodlandProps));
        public static final RegistrySupplier<Item> REAPERS_EMBRACE_CHESTPLATE = ITEMS.register("reapers_embrace_chestplate", () -> new ReaperArmorItem(ArmorItem.Type.CHESTPLATE, WoodlandProps));
        public static final RegistrySupplier<Item> REAPERS_EMBRACE_LEGGINGS = ITEMS.register("reapers_embrace_leggings", () -> new ReaperArmorItem(ArmorItem.Type.LEGGINGS, WoodlandProps));
        public static final RegistrySupplier<Item> REAPERS_EMBRACE_BOOTS = ITEMS.register("reapers_embrace_boots", () -> new ReaperArmorItem(ArmorItem.Type.BOOTS, WoodlandProps));

    // Woodland Spirit Armor
        public static final RegistrySupplier<Item> WOODLAND_SPIRIT_HELMET = ITEMS.register("woodland_spirit_helmet", () -> new WoodlandArmorItem(ArmorItem.Type.HELMET, WoodlandProps));
        public static final RegistrySupplier<Item> WOODLAND_SPIRIT_CHESTPLATE = ITEMS.register("woodland_spirit_chestplate", () -> new WoodlandArmorItem(ArmorItem.Type.CHESTPLATE, WoodlandProps));
        public static final RegistrySupplier<Item> WOODLAND_SPIRIT_LEGGINGS = ITEMS.register("woodland_spirit_leggings", () -> new WoodlandArmorItem(ArmorItem.Type.LEGGINGS, WoodlandProps));
        public static final RegistrySupplier<Item> WOODLAND_SPIRIT_BOOTS = ITEMS.register("woodland_spirit_boots", () -> new WoodlandArmorItem(ArmorItem.Type.BOOTS, WoodlandProps));

    // Arismas Awakening Armor
        public static final RegistrySupplier<Item> ARISMAS_AWAKENING_HELMET = ITEMS.register("arismas_awakening_helmet", () -> new ArismasArmorItem(ArmorItem.Type.HELMET, ReaperProps));
        public static final RegistrySupplier<Item> ARISMAS_AWAKENING_CHESTPLATE = ITEMS.register("arismas_awakening_chestplate", () -> new ArismasArmorItem(ArmorItem.Type.CHESTPLATE, ReaperProps));
        public static final RegistrySupplier<Item> ARISMAS_AWAKENING_LEGGINGS = ITEMS.register("arismas_awakening_leggings", () -> new ArismasArmorItem(ArmorItem.Type.LEGGINGS, ReaperProps));
        public static final RegistrySupplier<Item> ARISMAS_AWAKENING_BOOTS = ITEMS.register("arismas_awakening_boots", () -> new ArismasArmorItem(ArmorItem.Type.BOOTS, ReaperProps));
    //WEAPONS
        public static final RegistrySupplier<Item> HARBRINGER = ITEMS.register("harbringer", () ->
            new HarbringerSword(new Item.Properties()));
        public static final RegistrySupplier<Item> FURYS_CALL = ITEMS.register("furys_call", () ->
            new FurySword(new Item.Properties()));
        public static final RegistrySupplier<Item> HEAVENS_FALL = ITEMS.register("heavens_fall", () ->
            new HeavensSword(new Item.Properties()));
        public static final RegistrySupplier<Item> SHADOW_STALKER = ITEMS.register("shadow_stalker", () ->
            new ShadowSword(new Item.Properties()));

    public static void register() {
        ITEMS.register();
    }
}
