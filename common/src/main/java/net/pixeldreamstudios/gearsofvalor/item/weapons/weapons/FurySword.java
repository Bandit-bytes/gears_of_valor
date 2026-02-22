package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.dispatcher.GearsItemDispatcher;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;
import net.pixeldreamstudios.gearsofvalor.registry.TabRegistry;

public class FurySword extends SwordItem {
    public final GearsItemDispatcher dispatcher;

    public FurySword(Properties properties) {
        super(
                GearsWeaponMaterials.FURY_TIER,
                2,
                -2.5f,
                properties
                        .durability(2032)
                        .rarity(Rarity.RARE)
                        .arch$tab(TabRegistry.GEARS_TAB)
        );
        this.dispatcher = new GearsItemDispatcher();
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
        if (!level.isClientSide() && livingEntity instanceof Player player) {
            // dispatcher.firing(player, stack);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (e) -> e.broadcastBreakEvent(attacker.getUsedItemHand()));
        return true;
    }
}