package net.pixeldreamstudios.gearsofvalor.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * Shared armor item for every set. Set bonuses live in
 * {@link net.pixeldreamstudios.gearsofvalor.events.SetBonusHandler} and
 * {@link net.pixeldreamstudios.gearsofvalor.combat.GearsCombat}, so they run once per player rather than once per
 * armor piece, and anvil repair uses the material's repair ingredient.
 */
public class GearsArmorItem extends ArmorItem {
    private final ArmorSet armorSet;

    public GearsArmorItem(ArmorSet armorSet, Type type, Properties properties) {
        super(armorSet.material(), type, properties);
        this.armorSet = armorSet;
    }

    public ArmorSet armorSet() {
        return armorSet;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        String base = "item.gears_of_valor." + armorSet.tooltipKey() + ".tooltip.line";
        tooltip.add(Component.translatable(base + 1).withStyle(ChatFormatting.ITALIC));
        for (int i = 2; i <= armorSet.tooltipLines(); i++) {
            tooltip.add(Component.translatable(base + i).withStyle(ChatFormatting.GRAY));
        }
    }
}
