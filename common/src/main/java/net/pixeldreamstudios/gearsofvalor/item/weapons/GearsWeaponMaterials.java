package net.pixeldreamstudios.gearsofvalor.item.weapons;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GearsWeaponMaterials implements Tier {

    public static final Tier HARBRINGER_TIER = new GearsWeaponMaterials(
            1561,
            12.0f,
            5.0f,
            12,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.IRON_INGOT));

    public static final Tier FURY_TIER = new GearsWeaponMaterials(
            1561,
            10.0f,
            5.0f,
            12,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.IRON_INGOT));

    public static final Tier HEAVENS_TIER = new GearsWeaponMaterials(
            1561,
            11.0f,
            5.0f,
            12,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.IRON_INGOT));

    public static final Tier SHADOW_TIER = new GearsWeaponMaterials(
            1561,
            12.0f,
            5.0f,
            12,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.IRON_INGOT));


    int uses;
    float damage;
    float speed;
    int enchantmentValue;
    TagKey<Block> incorrectBlocksForDrops;
    Supplier<Ingredient> repairIngredient;

    public GearsWeaponMaterials(int uses, float damage, float speed, int enchantmentValue, TagKey<Block> incorrectBlocksForDrops, Supplier<Ingredient> repairIngredient) {
        this.uses = uses;
        this.damage = damage;
        this.speed = speed;
        this.enchantmentValue = enchantmentValue;
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
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
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
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
