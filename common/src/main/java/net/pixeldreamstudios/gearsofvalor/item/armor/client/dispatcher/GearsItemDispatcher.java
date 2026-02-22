package net.pixeldreamstudios.gearsofvalor.item.armor.client.dispatcher;


import mod.azure.azurelib.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class GearsItemDispatcher {
    private static final AzCommand FIRING_COMMAND = AzCommand.create("base_controller", "firing", AzPlayBehaviors.PLAY_ONCE);

    public void firing(Entity entity, ItemStack itemStack) {
        FIRING_COMMAND.sendForItem(entity, itemStack);
    }
}
