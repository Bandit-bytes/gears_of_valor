package net.pixeldreamstudios.gearsofvalor.item.weapons;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GearsWeaponMaterials implements Tier {

    public static final Tier HARBRINGER_TIER = new GearsWeaponMaterials(
            3,
            1561,
            12.0f,
            5.0f,
            12,
            () -> Ingredient.of(Items.IRON_INGOT)
    );

    public static final Tier FURY_TIER = new GearsWeaponMaterials(
            3, 1561, 10.0f, 5.0f, 12, () -> Ingredient.of(Items.IRON_INGOT)
    );

    public static final Tier HEAVENS_TIER = new GearsWeaponMaterials(
            3, 1561, 11.0f, 5.0f, 12, () -> Ingredient.of(Items.IRON_INGOT)
    );

    public static final Tier SHADOW_TIER = new GearsWeaponMaterials(
            3, 1561, 12.0f, 5.0f, 12, () -> Ingredient.of(Items.IRON_INGOT)
    );

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    public GearsWeaponMaterials(int level, int uses, float speed, float damage, int enchantmentValue,
                                Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}