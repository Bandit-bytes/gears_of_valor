package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;

/**
 * Passive "Soul Reap": hits wither the target; killing blows restore 2 hearts.
 * Ability "Shadow Step": vanish and reappear behind the creature you are looking at (up to 16 blocks).
 */
public class ShadowSword extends GearsSword {

    public ShadowSword(Properties properties) {
        super(GearsWeaponMaterials.SHADOW_TIER, 1, -2.5F, properties, "shadow_stalker", 20 * 10);
    }

    @Override
    protected void onHit(ServerLevel level, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isDeadOrDying()) {
            attacker.heal(4.0F);
            level.sendParticles(ParticleTypes.SOUL, target.getX(), target.getY() + target.getBbHeight() * 0.5,
                    target.getZ(), 12, 0.3, 0.4, 0.3, 0.03);
            level.playSound(null, target.blockPosition(), SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.0F);
        } else {
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0), attacker);
        }
    }

    @Override
    protected boolean useAbility(ServerLevel level, ServerPlayer player, ItemStack stack) {
        LivingEntity target = findLookTarget(player, 16.0);
        Vec3 dest = target == null ? null : findSpotBehind(level, player, target);
        if (dest == null) {
            level.playSound(null, player.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.4F, 2.0F);
            return false;
        }

        level.sendParticles(ParticleTypes.LARGE_SMOKE, player.getX(), player.getY() + 1.0, player.getZ(),
                20, 0.3, 0.6, 0.3, 0.02);

        double dx = target.getX() - dest.x;
        double dz = target.getZ() - dest.z;
        float yaw = (float) (Mth.atan2(dz, dx) * Mth.RAD_TO_DEG) - 90.0F;
        player.teleportTo(level, dest.x, dest.y, dest.z, yaw, player.getXRot());
        player.resetFallDistance();
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 40, 0, false, false, true));

        level.sendParticles(ParticleTypes.SOUL, dest.x, dest.y + 1.0, dest.z, 15, 0.3, 0.6, 0.3, 0.02);
        level.playSound(null, player.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.8F, 0.6F);
        return true;
    }

    /** A collision-free spot just behind the target, falling back to its sides. */
    private static Vec3 findSpotBehind(ServerLevel level, ServerPlayer player, LivingEntity target) {
        Vec3 facing = Vec3.directionFromRotation(0.0F, target.getYRot());
        double offset = target.getBbWidth() * 0.5 + 1.0;
        Vec3[] candidates = {
                facing.scale(-offset),
                facing.yRot(Mth.HALF_PI).scale(offset),
                facing.yRot(-Mth.HALF_PI).scale(offset)
        };
        for (Vec3 candidate : candidates) {
            Vec3 dest = target.position().add(candidate);
            if (level.noCollision(player, player.getBoundingBox().move(dest.subtract(player.position())))) {
                return dest;
            }
        }
        return null;
    }
}
