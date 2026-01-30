package net.pixeldreamstudios.gearsofvalor.item.armor.client.animator;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzItemAnimator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;
import org.jetbrains.annotations.NotNull;

public class ArismaAnimator extends AzItemAnimator {

    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(GearsOfValorMod.MOD_ID,
            "animations/armor/arismas_awakening.animation.json");

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

