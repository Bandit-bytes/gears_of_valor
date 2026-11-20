package net.pixeldreamstudios.gearsofvalor.events;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.pixeldreamstudios.gearsofvalor.item.armor.ArmorSet;
import net.pixeldreamstudios.gearsofvalor.util.PlayerState;

public final class SetBonusHandler {
    /** Passive set effects are re-applied with this duration so they wear off quickly once the set is removed. */
    private static final int AURA_DURATION = 60;

    private static final int PALADIN_LAST_STAND_COOLDOWN = 20 * 60;

    private SetBonusHandler() {}

    public static void init() {
        TickEvent.PLAYER_POST.register(SetBonusHandler::onPlayerTick);
        PlayerEvent.PLAYER_QUIT.register(PlayerState::clear);
    }

    private static void onPlayerTick(Player player) {
        if (!(player.level() instanceof ServerLevel level) || !player.isAlive() || player.isSpectator()) return;

        ArmorSet set = ArmorSet.wornBy(player);
        if (set == null) return;

        boolean pulse = player.tickCount % 20 == 0;
        switch (set) {
            case WOODLAND -> tickWoodland(level, player);
            case ARISMAS -> tickArismas(level, player, pulse);
            case PALADIN -> tickPaladin(level, player, pulse);
            case REAPER -> tickReaper(level, player, pulse);
        }
    }

    // Blessing of the Wildwood: agility, stealth while sneaking, protection on natural ground.
    private static void tickWoodland(ServerLevel level, Player player) {
        refresh(player, MobEffects.MOVEMENT_SPEED, 0);
        refresh(player, MobEffects.JUMP, 0);

        if (player.isCrouching()) {
            refresh(player, MobEffects.INVISIBILITY, 0);
        }

        BlockState ground = level.getBlockState(player.getOnPos());
        if (ground.is(BlockTags.DIRT) || ground.is(BlockTags.LEAVES) || ground.is(BlockTags.LOGS)) {
            refresh(player, MobEffects.DAMAGE_RESISTANCE, 0);
        }
    }

    // Aura of Ascendance: regenerate while standing still, slow nearby hostiles.
    private static void tickArismas(ServerLevel level, Player player, boolean pulse) {
        PlayerState state = PlayerState.of(player);
        double moved = player.distanceToSqr(state.lastX, state.lastY, state.lastZ);
        state.lastX = player.getX();
        state.lastY = player.getY();
        state.lastZ = player.getZ();
        state.stillTicks = moved < 1.0E-4 ? state.stillTicks + 1 : 0;

        if (state.stillTicks >= 20) {
            refresh(player, MobEffects.REGENERATION, 0);
            if (pulse) {
                level.sendParticles(ParticleTypes.ENCHANT, player.getX(), player.getY() + 1.0, player.getZ(),
                        8, 0.4, 0.6, 0.4, 0.4);
            }
        }

        if (pulse) {
            level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5),
                    e -> e instanceof Enemy && e.isAlive()
            ).forEach(mob -> mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, true)));
        }
    }

    // Aegis of the Lightbringer: tanky, heals allies, burns undead, Last Stand at low health.
    private static void tickPaladin(ServerLevel level, Player player, boolean pulse) {
        refresh(player, MobEffects.DAMAGE_RESISTANCE, 0);
        refresh(player, MobEffects.FIRE_RESISTANCE, 0);

        if (player.tickCount % 40 == 0) {
            level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(6),
                    p -> p != player && p.isAlive() && !p.isSpectator()
            ).forEach(ally -> refresh(ally, MobEffects.REGENERATION, 0));

            level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5),
                    e -> e.isAlive() && e.getType().is(EntityTypeTags.UNDEAD)
            ).forEach(undead -> {
                undead.hurt(level.damageSources().indirectMagic(player, player), 3.0F);
                undead.igniteForSeconds(3);
                level.sendParticles(ParticleTypes.END_ROD, undead.getX(), undead.getY() + undead.getBbHeight() * 0.6,
                        undead.getZ(), 6, 0.25, 0.4, 0.25, 0.02);
            });
        }

        Item chest = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        if (player.getHealth() < player.getMaxHealth() * 0.3F && !player.getCooldowns().isOnCooldown(chest)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 1, false, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1, false, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 1, false, true, true));
            player.getCooldowns().addCooldown(chest, PALADIN_LAST_STAND_COOLDOWN);
            level.playSound(null, player.blockPosition(), SoundEvents.BEACON_POWER_SELECT, SoundSource.PLAYERS, 1.0F, 1.4F);
            level.sendParticles(ParticleTypes.END_ROD, player.getX(), player.getY() + 1.0, player.getZ(),
                    40, 0.5, 0.8, 0.5, 0.1);
        }
    }

    // Harvest of the Fallen: weaken nearby hostiles, empowered near death (lifesteal and Death's Door are in GearsCombat).
    private static void tickReaper(ServerLevel level, Player player, boolean pulse) {
        if (player.getHealth() <= player.getMaxHealth() * 0.4F) {
            refresh(player, MobEffects.DAMAGE_BOOST, 1);
            refresh(player, MobEffects.MOVEMENT_SPEED, 0);
        }

        if (pulse) {
            level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(6),
                    e -> e instanceof Enemy && e.isAlive()
            ).forEach(enemy -> enemy.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 0, false, true)));
        }
    }

    /**
     * Keeps an aura effect topped up without re-sending it every tick, and without overriding a stronger or longer
     * copy of the same effect (e.g. from a potion or beacon).
     */
    private static void refresh(LivingEntity entity, Holder<MobEffect> effect, int amplifier) {
        MobEffectInstance current = entity.getEffect(effect);
        if (current != null && (current.getAmplifier() > amplifier
                || (current.getAmplifier() == amplifier && !current.endsWithin(AURA_DURATION - 20)))) {
            return;
        }
        entity.addEffect(new MobEffectInstance(effect, AURA_DURATION, amplifier, true, false, false));
    }
}
