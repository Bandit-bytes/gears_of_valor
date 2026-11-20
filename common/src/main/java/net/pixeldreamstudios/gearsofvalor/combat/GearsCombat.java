package net.pixeldreamstudios.gearsofvalor.combat;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.item.armor.ArmorSet;
import net.pixeldreamstudios.gearsofvalor.item.weapons.weapons.GearsSword;
import net.pixeldreamstudios.gearsofvalor.util.PlayerState;

/**
 * Damage-time hooks for weapon passives and armor set bonuses. Called from
 * {@link net.pixeldreamstudios.gearsofvalor.mixin.LivingEntityMixin} on the server only.
 */
public final class GearsCombat {
    private static final int DEATHS_DOOR_COOLDOWN = 20 * 60 * 5;
    private static final int THORNROOT_COOLDOWN = 20 * 4;

    private GearsCombat() {}

    public static boolean isMelee(DamageSource source) {
        Entity attacker = source.getEntity();
        return attacker != null && source.getDirectEntity() == attacker
                && (source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.MOB_ATTACK)
                || source.is(DamageTypes.MOB_ATTACK_NO_AGGRO));
    }

    public static float modifyIncomingDamage(LivingEntity victim, DamageSource source, float amount) {
        if (victim.level().isClientSide() || amount <= 0.0F) return amount;

        boolean melee = isMelee(source);
        LivingEntity attacker = source.getEntity() instanceof LivingEntity living ? living : null;
        float result = amount;

        if (melee && attacker != null) {
            ItemStack weapon = attacker.getMainHandItem();
            if (weapon.getItem() instanceof GearsSword sword) {
                result = sword.modifyDamage(weapon, victim, attacker, result);
            }
            // Arismas: enchanted weapons hit harder (+5% per enchantment, up to +25%).
            if (weapon.isEnchanted() && ArmorSet.ARISMAS.isWornBy(attacker)) {
                result *= 1.0F + Math.min(0.25F, 0.05F * weapon.getEnchantmentTags().size());
            }
        }

        // Paladin: take 25% less damage from the undead.
        if (attacker != null && attacker.getMobType() == MobType.UNDEAD && ArmorSet.PALADIN.isWornBy(victim)) {
            result *= 0.75F;
        }

        // Woodland: roots and pricks melee attackers.
        if (melee && attacker != null && victim instanceof Player player && ArmorSet.WOODLAND.isWornBy(player)) {
            thornroot(player, attacker);
        }

        // Reaper: lifesteal on melee hits, stronger near death. Skipped while the target is still in i-frames.
        if (melee && attacker instanceof Player player && victim.invulnerableTime <= 10
                && ArmorSet.REAPER.isWornBy(player)) {
            float ratio = player.getHealth() <= player.getMaxHealth() * 0.4F ? 0.25F : 0.10F;
            player.heal(Math.min(result, victim.getHealth()) * ratio);
        }

        return result;
    }

    private static void thornroot(Player player, LivingEntity attacker) {
        if (attacker == player || !(player.level() instanceof ServerLevel level)) return;
        PlayerState state = PlayerState.of(player);
        long now = level.getGameTime();
        if (now < state.thornrootReadyAt) return;
        state.thornrootReadyAt = now + THORNROOT_COOLDOWN;

        attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, false, true));
        attacker.hurt(level.damageSources().thorns(player), 2.0F);
        level.sendParticles(ParticleTypes.SPORE_BLOSSOM_AIR, attacker.getX(), attacker.getY() + 0.2, attacker.getZ(),
                12, 0.4, 0.1, 0.4, 0.0);
        level.playSound(null, attacker.blockPosition(), SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.PLAYERS, 1.0F, 0.8F);
    }

    /**
     * Reaper's Death's Door: called when vanilla's totem check fails. Returns true if the player was saved.
     */
    public static boolean tryCheatDeath(LivingEntity entity, DamageSource source) {
        if (!(entity instanceof Player player) || !(player.level() instanceof ServerLevel level)) return false;
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || !ArmorSet.REAPER.isWornBy(player)) return false;

        Item chest = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        if (player.getCooldowns().isOnCooldown(chest)) return false;

        player.setHealth(6.0F);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 3, false, true, true));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, 1, false, true, true));
        player.getCooldowns().addCooldown(chest, DEATHS_DOOR_COOLDOWN);

        level.sendParticles(ParticleTypes.SOUL, player.getX(), player.getY() + 1.0, player.getZ(),
                40, 0.5, 0.8, 0.5, 0.05);
        level.playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 0.8F, 0.6F);
        player.displayClientMessage(Component.translatable("message.gears_of_valor.deaths_door")
                .withStyle(ChatFormatting.DARK_RED), true);
        return true;
    }
}
