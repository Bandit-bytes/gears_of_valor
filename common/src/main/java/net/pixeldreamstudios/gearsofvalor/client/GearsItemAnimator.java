package net.pixeldreamstudios.gearsofvalor.client;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzItemAnimator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import org.jetbrains.annotations.NotNull;

/** Animator for any Gears of Valor item; points at {@code animations/<path>.animation.json}. */
public class GearsItemAnimator extends AzItemAnimator {
    private final ResourceLocation animations;

    public GearsItemAnimator(String folder, String name) {
        this.animations = GearsOfValorMod.modResource("animations/" + folder + name + ".animation.json");
    }

    @Override
    public void registerControllers(AzAnimationControllerContainer<ItemStack> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(ItemStack animatable) {
        return animations;
    }
}
