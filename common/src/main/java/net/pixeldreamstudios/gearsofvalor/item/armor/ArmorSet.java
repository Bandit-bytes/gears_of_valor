package net.pixeldreamstudios.gearsofvalor.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * The four Gears of Valor armor sets. Materials are looked up lazily so that touching this enum never forces the
 * armor materials to register before the item registry is ready.
 */
public enum ArmorSet {
    WOODLAND("woodland_armor", 5, () -> GearsArmorMaterials.WOODLAND),
    ARISMAS("arismas_armor", 4, () -> GearsArmorMaterials.ARISMAS),
    PALADIN("paladin_armor", 5, () -> GearsArmorMaterials.PALADIN),
    REAPER("reaper_armor", 4, () -> GearsArmorMaterials.REAPER);

    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    private final String tooltipKey;
    private final int tooltipLines;
    private final Supplier<Holder<ArmorMaterial>> material;

    ArmorSet(String tooltipKey, int tooltipLines, Supplier<Holder<ArmorMaterial>> material) {
        this.tooltipKey = tooltipKey;
        this.tooltipLines = tooltipLines;
        this.material = material;
    }

    public String tooltipKey() {
        return tooltipKey;
    }

    public int tooltipLines() {
        return tooltipLines;
    }

    public Holder<ArmorMaterial> material() {
        return material.get();
    }

    public boolean isWornBy(LivingEntity entity) {
        return countWorn(entity) == ARMOR_SLOTS.length;
    }

    public int countWorn(LivingEntity entity) {
        int count = 0;
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            if (matches(entity.getItemBySlot(slot))) count++;
        }
        return count;
    }

    private boolean matches(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem armor && armor.getMaterial().equals(material());
    }

    /** Returns the set the entity is wearing in full, or {@code null}. */
    public static @Nullable ArmorSet wornBy(LivingEntity entity) {
        ItemStack head = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (!(head.getItem() instanceof GearsArmorItem item)) return null;
        ArmorSet set = item.armorSet();
        return set.isWornBy(entity) ? set : null;
    }
}
