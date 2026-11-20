package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.item.weapons.GearsWeaponMaterials;

/**
 * Passive "Judgment": +40% damage against the undead.
 * Ability "Heaven's Wrath": calls a bolt of holy lightning onto the creature you are looking at (up to 24 blocks),
 * dealing heavy damage (more to the undead) and splashing nearby foes.
 */
public class HeavensSword extends GearsSword {

    public HeavensSword(Properties properties) {
        super(GearsWeaponMaterials.HEAVENS_TIER, 0, -2.5F, properties, "heavens_fall", 20 * 15);
    }

    @Override
    public float modifyDamage(ItemStack stack, LivingEntity target, LivingEntity attacker, float amount) {
        return target.getType().is(EntityTypeTags.UNDEAD) ? amount * 1.4F : amount;
    }

    @Override
    protected boolean useAbility(ServerLevel level, ServerPlayer player, ItemStack stack) {
        LivingEntity target = findLookTarget(player, 24.0);
        if (target == null) {
            level.playSound(null, player.blockPosition(), SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 0.6F, 1.6F);
            return false;
        }

        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        if (bolt != null) {
            bolt.moveTo(target.position());
            bolt.setVisualOnly(true); // no fire spread, no charged creepers, no hurting the caster
            level.addFreshEntity(bolt);
        }

        float damage = target.getType().is(EntityTypeTags.UNDEAD) ? 16.0F : 10.0F;
        target.hurt(level.damageSources().indirectMagic(player, player), damage);
        target.igniteForSeconds(4);

        level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(2.5),
                e -> e != target && isFoe(player, e)
        ).forEach(foe -> foe.hurt(level.damageSources().indirectMagic(player, player), damage * 0.5F));
        return true;
    }
}
