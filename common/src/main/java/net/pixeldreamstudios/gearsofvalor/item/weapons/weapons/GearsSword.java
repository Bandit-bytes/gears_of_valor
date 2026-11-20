package net.pixeldreamstudios.gearsofvalor.item.weapons.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Base for the Gears of Valor weapons: a real {@link SwordItem} (sweeping, enchantments, one durability per hit) with
 * a damage-time passive ({@link #modifyDamage}), an on-hit effect ({@link #onHit}) and a right-click ability.
 */
public abstract class GearsSword extends SwordItem {
    private final String id;
    private final int abilityCooldown;

    protected GearsSword(Tier tier, int attackDamage, float attackSpeed, Properties properties, String id, int abilityCooldown) {
        super(tier, attackDamage, attackSpeed, properties);
        this.id = id;
        this.abilityCooldown = abilityCooldown;
    }

    /** Called while the hit is being applied; return the (possibly changed) damage. Server side only. */
    public float modifyDamage(ItemStack stack, LivingEntity target, LivingEntity attacker, float amount) {
        return amount;
    }

    /** Called after a successful melee hit. Server side only. */
    protected void onHit(ServerLevel level, ItemStack stack, LivingEntity target, LivingEntity attacker) {}

    /** Performs the right-click ability. Return true if it fired (starts the cooldown). Server side only. */
    protected abstract boolean useAbility(ServerLevel level, ServerPlayer player, ItemStack stack);

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (attacker.level() instanceof ServerLevel level) {
            onHit(level, stack, target, attacker);
        }
        return result;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (hand != InteractionHand.MAIN_HAND) return InteractionResultHolder.pass(stack);
        // The client always forwards the use to the server, which decides whether the ability can fire.
        if (!(level instanceof ServerLevel serverLevel) || !(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResultHolder.pass(stack);
        }
        if (useAbility(serverLevel, serverPlayer, stack)) {
            player.getCooldowns().addCooldown(this, abilityCooldown);
            stack.hurtAndBreak(3, player, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        String base = "item.gears_of_valor." + id;
        tooltip.add(Component.translatable(base + ".passive").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(base + ".passive.desc").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable(base + ".ability").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable(base + ".ability.desc").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tooltip.gears_of_valor.cooldown", abilityCooldown / 20)
                .withStyle(ChatFormatting.DARK_GRAY));
    }

    // ---- helpers shared by the abilities ----

    /** First living entity along the player's line of sight, stopping at blocks. */
    protected static @Nullable LivingEntity findLookTarget(Player player, double range) {
        Vec3 eye = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = eye.add(look.scale(range));
        HitResult block = player.level().clip(new ClipContext(eye, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if (block.getType() != HitResult.Type.MISS) end = block.getLocation();

        AABB search = player.getBoundingBox().expandTowards(look.scale(range)).inflate(1.0);
        EntityHitResult hit = ProjectileUtil.getEntityHitResult(player, eye, end, search,
                e -> e instanceof LivingEntity && e.isPickable() && !e.isSpectator() && e.isAlive(),
                eye.distanceToSqr(end));
        return hit != null && hit.getEntity() instanceof LivingEntity living ? living : null;
    }

    /** Hostile mobs, or any mob currently targeting the player; never the player's own pets. */
    protected static boolean isFoe(Player player, Entity entity) {
        if (entity == player || !(entity instanceof LivingEntity living) || !living.isAlive()) return false;
        if (entity instanceof TamableAnimal pet && pet.isOwnedBy(player)) return false;
        return entity instanceof Enemy || (entity instanceof Mob mob && mob.getTarget() == player);
    }
}
