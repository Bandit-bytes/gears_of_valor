package net.pixeldreamstudios.gearsofvalor.item.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PureStoneItem extends Item {

    public PureStoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag
    ) {
        if (Screen.hasShiftDown())
            tooltipComponents.add(Component.translatable("tooltip.cursed_stone_info_2").withStyle(ChatFormatting.RED));
        else {
            tooltipComponents.add(Component.translatable("item.stone.hold_shift"));
            tooltipComponents.add(Component.translatable("tooltip.stone_info").withStyle(ChatFormatting.GREEN));
        }
    }
}