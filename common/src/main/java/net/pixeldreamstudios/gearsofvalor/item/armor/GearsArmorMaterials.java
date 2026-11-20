package net.pixeldreamstudios.gearsofvalor.item.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

import java.util.function.Supplier;

/**
 * Durability multipliers use the vanilla scale (iron 15, diamond 33, netherite 37). Knockback resistance is per
 * piece (netherite uses 0.1); the attribute caps at 1.0.
 */
public enum GearsArmorMaterials implements ArmorMaterial {

    ARISMAS(
            "arismas",
            30,
            new int[]{2, 5, 6, 2},
            10,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            1.0F,
            0.05F,
            () -> Ingredient.of(ItemRegistry.ARISMA_SCALE.get())
    ),

    PALADIN(
            "paladin",
            33,
            new int[]{3, 6, 8, 3},
            15,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.0F,
            0.1F,
            () -> Ingredient.of(ItemRegistry.NOBLE_GEM.get())
    ),

    REAPER(
            "reaper",
            36,
            new int[]{4, 7, 9, 4},
            20,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            3.0F,
            0.15F,
            () -> Ingredient.of(ItemRegistry.SOULINITE.get())
    ),

    WOODLAND(
            "woodland",
            25,
            new int[]{1, 3, 5, 2},
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            1.0F,
            0.0F,
            () -> Ingredient.of(ItemRegistry.TAINTED_IRON.get())
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