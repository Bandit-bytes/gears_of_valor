package net.pixeldreamstudios.gearsofvalor.item.armor.sets;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.gearsofvalor.item.armor.GearsArmorMaterials;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.dispatcher.GearsArmorDispatcher;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WoodlandArmorItem extends ArmorItem {

    public final GearsArmorDispatcher DISPATCHER;

    public WoodlandArmorItem(Type type, Properties properties) {
        super(GearsArmorMaterials.WOODLAND, type, properties
        );
        this.DISPATCHER = new GearsArmorDispatcher();
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (hasFullSet(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 220, 0, false, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 220, 0, false, false, false));

                if (player.isCrouching() && player.tickCount % 20 == 0) {
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 40, 0, false, false, false));
                }

                level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(2), e ->
                        e != player && !(e instanceof Player) && e.isAlive()
                ).forEach(mob -> {
                    double dx = mob.getX() - player.getX();
                    double dz = mob.getZ() - player.getZ();
                    double dist = Math.sqrt(dx * dx + dz * dz);
                    if (dist > 0.01) {
                        mob.setDeltaMovement(dx / dist * 0.1, mob.getDeltaMovement().y, dz / dist * 0.1);
                    }
                });

                if (level.getBlockState(player.blockPosition().below()).getBlock().defaultMapColor().col == 0x4C7639 ||
                        level.getBlockState(player.blockPosition().below()).getBlock().defaultMapColor().col == 0x3E5F3D) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 0, false, false, false));
                }
            }
        }
    }
    private boolean hasFullSet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof WoodlandArmorItem &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof WoodlandArmorItem &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof WoodlandArmorItem &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof WoodlandArmorItem;
    }
    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag tooltipFlag
    ) {
        tooltip.add(Component.translatable("item.gears_of_valor.woodland_armor.tooltip.line1").withStyle(ChatFormatting.ITALIC));
        tooltip.add(Component.translatable("item.gears_of_valor.woodland_armor.tooltip.line2"));
        tooltip.add(Component.translatable("item.gears_of_valor.woodland_armor.tooltip.line3"));
        tooltip.add(Component.translatable("item.gears_of_valor.woodland_armor.tooltip.line4"));
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(Items.IRON_INGOT);
    }
}
