package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.dispatcher.GearsItemDispatcher;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.HeavensRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.ShadowRenderer;
import net.pixeldreamstudios.gearsofvalor.registry.TabRegistry;

public class ShadowSword extends Item {
    public final GearsItemDispatcher dispatcher;

    public ShadowSword(Properties properties) {
        super(new Properties().attributes(SwordItem.createAttributes(GearsWeaponMaterials.SHADOW_TIER, 1, -2.5f)).durability(375).rarity(Rarity.RARE).arch$tab(TabRegistry.GEARS_TAB));
        this.dispatcher = new GearsItemDispatcher();
    }
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
        if (livingEntity instanceof Player player && !level.isClientSide()) {

            // This is where you now trigger an animation to play
        }
    }
}
