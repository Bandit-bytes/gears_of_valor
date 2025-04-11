package net.pixeldreamstudios.gearsofvalor.item.armor.sets;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.gearsofvalor.item.armor.GearsArmorMaterials;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.dispatcher.GearsArmorDispatcher;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.PaladinArmorRenderer;

import java.util.List;

public class PaladinArmorItem extends GearsArmorItem {
    private int cooldown = 0;

    public final GearsArmorDispatcher DISPATCHER;

    public PaladinArmorItem(Type type, Properties properties) {
        super(GearsArmorMaterials.PALADIN, type, PaladinArmorRenderer::new, properties
        );
        this.DISPATCHER = new GearsArmorDispatcher();
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (hasFullSet(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 220, 0, false, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 220, 0, false, false, false));

                // Buff nearby players
                level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(5), p -> p != player).forEach(ally -> {
                    ally.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false, false));
                    ally.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0, false, false, false));
                });

                // Smite undead
                level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4), e ->
                        e != player && e.getType().getCategory().getName().equals("undead")
                ).forEach(undead -> {
                    if (level.getGameTime() % 40 == 0) {
                        undead.setRemainingFireTicks(2);
                    }
                });
                if (player.getHealth() / player.getMaxHealth() < 0.3f && cooldown <= 0) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 1, false, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0, false, false, false));
                    cooldown = 200;
                }

                if (cooldown > 0) {
                    cooldown--;
                }
            }
        }
    }
    private boolean hasFullSet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof PaladinArmorItem &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof PaladinArmorItem &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof PaladinArmorItem &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof PaladinArmorItem;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.gears_of_valor.paldin_armor.tooltip.line1").withStyle(ChatFormatting.ITALIC));
        tooltip.add(Component.translatable("item.gears_of_valor.paldin_armor.tooltip.line2"));
        tooltip.add(Component.translatable("item.gears_of_valor.paldin_armor.tooltip.line3"));
        tooltip.add(Component.translatable("item.gears_of_valor.paldin_armor.tooltip.line4"));
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(Items.IRON_INGOT);
    }
}
