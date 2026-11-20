package net.pixeldreamstudios.gearsofvalor.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.pixeldreamstudios.gearsofvalor.combat.GearsCombat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyVariable(method = "hurt", at = @At("HEAD"), argsOnly = true)
    private float gearsofvalor$modifyIncomingDamage(float amount, DamageSource source) {
        return GearsCombat.modifyIncomingDamage((LivingEntity) (Object) this, source, amount);
    }

    @Inject(method = "checkTotemDeathProtection", at = @At("RETURN"), cancellable = true)
    private void gearsofvalor$deathsDoor(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValueZ() && GearsCombat.tryCheatDeath((LivingEntity) (Object) this, source)) {
            cir.setReturnValue(true);
        }
    }
}
