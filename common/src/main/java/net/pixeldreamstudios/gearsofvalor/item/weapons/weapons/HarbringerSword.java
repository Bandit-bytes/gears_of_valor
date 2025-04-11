package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.gearsofvalor.item.armor.client.dispatcher.GearsItemDispatcher;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;
import net.pixeldreamstudios.gearsofvalor.item.weapons.renderer.HarbringerRenderer;

public class HarbringerSword extends WeaponItem {
    public final GearsItemDispatcher dispatcher;

    public HarbringerSword( Properties properties) {
        super(GearsWeaponMaterials.HARBRINGER_TIER, HarbringerRenderer::new, properties
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
