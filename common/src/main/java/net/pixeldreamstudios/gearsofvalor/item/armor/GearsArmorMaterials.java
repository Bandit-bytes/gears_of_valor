package net.pixeldreamstudios.gearsofvalor.item.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

import java.util.function.Supplier;

public enum GearsArmorMaterials implements ArmorMaterial {

    ARISMAS(
            "arismas",
            15,
            new int[]{2, 5, 6, 2},
            10,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            1.0F,
            0.5F,
            () -> Ingredient.of(Items.COPPER_INGOT)
    ),

    PALADIN(
            "paladin",
            25,
            new int[]{3, 6, 8, 3},
            15,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.0F,
            2.0F,
            () -> Ingredient.of(Items.DIAMOND)
    ),

    REAPER(
            "reaper",
            30,
            new int[]{4, 7, 9, 4},
            20,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            3.0F,
            3.0F,
            () -> Ingredient.of(Items.NETHERITE_INGOT)
    ),

    WOODLAND(
            "woodland",
            10,
            new int[]{1, 3, 5, 2},
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            1.0F,
            0.0F,
            () -> Ingredient.of(Items.OAK_LOG)
    );

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protection;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    GearsArmorMaterials(
            String name,
            int durabilityMultiplier,
            int[] protection,
            int enchantmentValue,
            SoundEvent equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protection = protection;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[getIndex(type)] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return protection[getIndex(type)];
    }

    private static int getIndex(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> 0;
            case LEGGINGS -> 1;
            case CHESTPLATE -> 2;
            case HELMET -> 3;
        };
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return GearsOfValorMod.MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}