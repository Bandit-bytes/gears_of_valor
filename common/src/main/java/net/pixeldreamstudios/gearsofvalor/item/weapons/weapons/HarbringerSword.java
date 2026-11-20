package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;

/**
 * Passive "Omen": hits mark the target (Glowing); marked targets take 20% more damage from this blade.
 * Ability "Herald's Toll": a bell-toll shockwave that damages, knocks back and marks nearby foes.
 */
public class HarbringerSword extends GearsSword {

    public HarbringerSword(Properties properties) {
        super(GearsWeaponMaterials.HARBRINGER_TIER, 3, -2.0F, properties, "harbringer", 20 * 12);
    }

    @Override
    public float modifyDamage(ItemStack stack, LivingEntity target, LivingEntity attacker, float amount) {
        return target.hasEffect(MobEffects.GLOWING) ? amount * 1.2F : amount;
    }

    @Override
    protected void onHit(ServerLevel level, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0, false, false), attacker);
    }

    @Override
    protected boolean useAbility(ServerLevel level, ServerPlayer player, ItemStack stack) {
        level.playSound(null, player.blockPosition(), SoundEvents.BELL_BLOCK, SoundSource.PLAYERS, 1.5F, 0.6F);
        level.sendParticles(ParticleTypes.SONIC_BOOM, player.getX(), player.getY() + 1.0, player.getZ(), 1, 0, 0, 0, 0);

        level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5), e -> isFoe(player, e))
                .forEach(foe -> {
                    foe.hurt(level.damageSources().indirectMagic(player, player), 6.0F);
                    foe.knockback(1.2, player.getX() - foe.getX(), player.getZ() - foe.getZ());
                    foe.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0, false, false), player);
                    level.sendParticles(ParticleTypes.END_ROD, foe.getX(), foe.getY() + foe.getBbHeight() * 0.5,
                            foe.getZ(), 6, 0.2, 0.3, 0.2, 0.05);
                });
        return true;
    }
}
