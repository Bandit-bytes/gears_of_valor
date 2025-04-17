package net.pixeldreamstudios.gearsofvalor.item.armor;

import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;

import static net.pixeldreamstudios.gearsofvalor.item.armor.GearsArmorMaterialsRegister.registerArmorMaterial;

public class GearsArmorMaterials {
    public static final Holder<ArmorMaterial> ARISMAS = registerArmorMaterial(GearsOfValorMod.MOD_ID, "arismas", Util.make(new EnumMap<>(ArmorItem.Type.class), enumMap -> {
        enumMap.put(ArmorItem.Type.BOOTS, 2);
        enumMap.put(ArmorItem.Type.LEGGINGS, 5);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 6);
        enumMap.put(ArmorItem.Type.HELMET, 2);
        enumMap.put(ArmorItem.Type.BODY, 5);
    }), 10, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.5F, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final Holder<ArmorMaterial> PALADIN = registerArmorMaterial(GearsOfValorMod.MOD_ID, "paladin", Util.make(new EnumMap(ArmorItem.Type.class), enumMap -> {
        enumMap.put(ArmorItem.Type.BOOTS, 3);
        enumMap.put(ArmorItem.Type.LEGGINGS, 6);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 8);
        enumMap.put(ArmorItem.Type.HELMET, 3);
        enumMap.put(ArmorItem.Type.BODY, 6);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 2.0F, () -> Ingredient.of(Items.DIAMOND));

    public static final Holder<ArmorMaterial> REAPER = registerArmorMaterial(GearsOfValorMod.MOD_ID, "reaper", Util.make(new EnumMap<>(ArmorItem.Type.class), enumMap -> {
        enumMap.put(ArmorItem.Type.BOOTS, 4);
        enumMap.put(ArmorItem.Type.LEGGINGS, 7);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 9);
        enumMap.put(ArmorItem.Type.HELMET, 4);
        enumMap.put(ArmorItem.Type.BODY, 7);
    }), 20, SoundEvents.ARMOR_EQUIP_LEATHER, 3.0F, 3.0F, () -> Ingredient.of(Items.NETHERITE_INGOT));

    public static final Holder<ArmorMaterial> WOODLAND = registerArmorMaterial(GearsOfValorMod.MOD_ID, "woodland", Util.make(new EnumMap<>(ArmorItem.Type.class), enumMap -> {
        enumMap.put(ArmorItem.Type.BOOTS, 1);
        enumMap.put(ArmorItem.Type.LEGGINGS, 3);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 5);
        enumMap.put(ArmorItem.Type.HELMET, 2);
        enumMap.put(ArmorItem.Type.BODY, 4);
    }), 5, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(Items.OAK_LOG));
}
