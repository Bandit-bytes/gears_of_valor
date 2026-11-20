package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;
import net.pixeldreamstudios.gearsofvalor.util.PlayerState;

/**
 * Passive "Rising Fury": consecutive hits build up to 5 Fury stacks (+6% damage each); the fifth hit ignites the
 * target and spends the stacks. Stacks fade after 4 seconds without a hit.
 * Ability "Battle Cry": Strength and Speed for 6 seconds (Strength II with 3+ stacks) and weakens nearby foes.
 */
public class FurySword extends GearsSword {
    private static final int MAX_STACKS = 5;
    private static final int STACK_TIMEOUT = 80;

    public FurySword(Properties properties) {
        super(GearsWeaponMaterials.FURY_TIER, 2, -2.5F, properties, "furys_call", 20 * 20);
    }

    private static int currentStacks(Player player) {
        PlayerState state = PlayerState.of(player);
        if (player.level().getGameTime() - state.furyLastHit > STACK_TIMEOUT) state.furyStacks = 0;
        return state.furyStacks;
    }

    @Override
    public float modifyDamage(ItemStack stack, LivingEntity target, LivingEntity attacker, float amount) {
        if (!(attacker instanceof Player player)) return amount;
        return amount * (1.0F + 0.06F * currentStacks(player));
    }

    @Override
    protected void onHit(ServerLevel level, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player player)) return;
        PlayerState state = PlayerState.of(player);
        int stacks = currentStacks(player) + 1;
        state.furyLastHit = level.getGameTime();

        if (stacks >= MAX_STACKS) {
            state.furyStacks = 0;
            target.setSecondsOnFire(4);
            level.sendParticles(ParticleTypes.FLAME, target.getX(), target.getY() + target.getBbHeight() * 0.5,
                    target.getZ(), 20, 0.3, 0.4, 0.3, 0.05);
            level.playSound(null, target.blockPosition(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 0.8F);
        } else {
            state.furyStacks = stacks;
            level.sendParticles(ParticleTypes.CRIT, target.getX(), target.getY() + target.getBbHeight() * 0.5,
                    target.getZ(), 4 * stacks, 0.3, 0.3, 0.3, 0.1);
        }
    }

    @Override
    protected boolean useAbility(ServerLevel level, ServerPlayer player, ItemStack stack) {
        int strength = currentStacks(player) >= 3 ? 1 : 0;
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, strength, false, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 0, false, true, true));
        level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(6), e -> isFoe(player, e))
                .forEach(foe -> foe.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), player));
        level.playSound(null, player.blockPosition(), SoundEvents.RAVAGER_ROAR, SoundSource.PLAYERS, 1.0F, 1.2F);
        level.sendParticles(ParticleTypes.ANGRY_VILLAGER, player.getX(), player.getY() + 2.0, player.getZ(),
                6, 0.5, 0.3, 0.5, 0.0);
        return true;
    }
}
