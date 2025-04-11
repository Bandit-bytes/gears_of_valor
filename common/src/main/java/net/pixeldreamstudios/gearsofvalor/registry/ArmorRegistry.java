package net.pixeldreamstudios.gearsofvalor.registry;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.ArismasArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.PaladinArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.ReaperArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.armor.sets.WoodlandArmorItem;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.HarbringerSword;

public class ArmorRegistry {
    public static void init() {
        Item.Properties PaladinProps = new Item.Properties().durability(490).stacksTo(1).rarity(Rarity.EPIC).arch$tab(TabRegistry.GEARS_TAB);
        Item.Properties WoodlandProps = new Item.Properties().durability(440).stacksTo(1).rarity(Rarity.RARE).arch$tab(TabRegistry.GEARS_TAB);
        Item.Properties ReaperProps = new Item.Properties().durability(510).stacksTo(1).rarity(Rarity.EPIC).arch$tab(TabRegistry.GEARS_TAB);

        // Paladin Armor
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "paladin_helmet", () -> new PaladinArmorItem(ArmorItem.Type.HELMET, PaladinProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "paladin_chestplate", () -> new PaladinArmorItem(ArmorItem.Type.CHESTPLATE, PaladinProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "paladin_leggings", () -> new PaladinArmorItem(ArmorItem.Type.LEGGINGS, PaladinProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "paladin_boots", () -> new PaladinArmorItem(ArmorItem.Type.BOOTS, PaladinProps));

        // Reapers Embrace Armor
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "reapers_embrace_helmet", () -> new ReaperArmorItem(ArmorItem.Type.HELMET, WoodlandProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "reapers_embrace_chestplate", () -> new ReaperArmorItem(ArmorItem.Type.CHESTPLATE, WoodlandProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "reapers_embrace_leggings", () -> new ReaperArmorItem(ArmorItem.Type.LEGGINGS, WoodlandProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "reapers_embrace_boots", () -> new ReaperArmorItem(ArmorItem.Type.BOOTS, WoodlandProps));

        // Woodland Spirit Armor
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "woodland_spirit_helmet", () -> new WoodlandArmorItem(ArmorItem.Type.HELMET, WoodlandProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "woodland_spirit_chestplate", () -> new WoodlandArmorItem(ArmorItem.Type.CHESTPLATE, WoodlandProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "woodland_spirit_leggings", () -> new WoodlandArmorItem(ArmorItem.Type.LEGGINGS, WoodlandProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "woodland_spirit_boots", () -> new WoodlandArmorItem(ArmorItem.Type.BOOTS, WoodlandProps));

        // Arismas Awakening Armor
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "arismas_awakening_helmet", () -> new ArismasArmorItem(ArmorItem.Type.HELMET, ReaperProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "arismas_awakening_chestplate", () -> new ArismasArmorItem(ArmorItem.Type.CHESTPLATE, ReaperProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "arismas_awakening_leggings", () -> new ArismasArmorItem(ArmorItem.Type.LEGGINGS, ReaperProps));
        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "arismas_awakening_boots", () -> new ArismasArmorItem(ArmorItem.Type.BOOTS, ReaperProps));

        ArmorRegistering.registerItem(GearsOfValorMod.MOD_ID, "harbringer", () ->
                new HarbringerSword(
                        new Item.Properties()
                                .attributes(SwordItem.createAttributes(GearsWeaponMaterials.HARBRINGER_TIER, 3, -2f))
                                .durability(375)
                                .rarity(Rarity.RARE)
                                .arch$tab(TabRegistry.GEARS_TAB)
                )
        );
    }
}
