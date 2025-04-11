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
import net.pixeldreamstudios.gearsofvalor.item.armor.client.renderer.ReaperArmorRenderer;

import java.util.List;

public class ReaperArmorItem extends GearsArmorItem {

    public final GearsArmorDispatcher DISPATCHER;

    public ReaperArmorItem(Type type, Properties properties) {
        super(GearsArmorMaterials.REAPER, type, ReaperArmorRenderer::new, properties
        );
        this.DISPATCHER = new GearsArmorDispatcher();
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (hasFullSet(player)) {
                {
                    if (player.getHealth() / player.getMaxHealth() <= 0.4f) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1, false, true, true));
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0, false, true, true));
                    }
                    level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5), e ->
                            e != player && !(e instanceof Player) && e.isAlive()
                    ).forEach(enemy -> {
                        enemy.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, false, false));
                    });
                    // lifesteal
                    if (player.getAttackStrengthScale(1.0F) > 0.9F && player.getLastHurtMob() != null) {
                        player.heal(2.0F);
                    }
                }
            }
        }
    }
    private boolean hasFullSet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ReaperArmorItem &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ReaperArmorItem &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ReaperArmorItem &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ReaperArmorItem;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.gears_of_valor.reaper_armor.tooltip").withStyle(ChatFormatting.ITALIC));
    }
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(Items.IRON_INGOT);
    }
}
