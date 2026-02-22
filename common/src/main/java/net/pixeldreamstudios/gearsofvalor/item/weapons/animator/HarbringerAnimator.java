package net.pixeldreamstudios.gearsofvalor.item.weapons.animator;


import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzItemAnimator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import org.jetbrains.annotations.NotNull;

public class HarbringerAnimator extends AzItemAnimator {
    private static final ResourceLocation ANIMATIONS = new ResourceLocation(
            GearsOfValorMod.MOD_ID,
            "animations/items/harbringer.animation.json"
    );

    @Override
    public void registerControllers(AzAnimationControllerContainer<ItemStack> animationControllerContainer) {
        animationControllerContainer.add(
                AzAnimationController.builder(this, "base_controller")
                        .build()
        );
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(ItemStack animatable) {
        return ANIMATIONS;
    }
}