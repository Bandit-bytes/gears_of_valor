package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.dispatcher.GearsItemDispatcher;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.HeavensRenderer;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.ShadowRenderer;

public class ShadowSword extends WeaponItem {
    public final GearsItemDispatcher dispatcher;

    public ShadowSword(Properties properties) {
        super(GearsWeaponMaterials.SHADOW_TIER, ShadowRenderer::new, properties
        );
        // Create the instance of the class here to use later.
        this.dispatcher = new GearsItemDispatcher();
    }


    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
        if (livingEntity instanceof Player player && !level.isClientSide()) {

            // This is where you now trigger an animation to play
//            dispatcher.firing(player, stack);
        }
    }
}
