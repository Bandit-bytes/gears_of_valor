package net.pixeldreamstudios.gearsofvalor.events;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Noble Gem ritual: drop a Cursed Moonshard and a Purification Stone into a water cauldron heated from below
 * (fire, soul fire, lit campfire, lava or magma). Consumes one of each item and one level of water.
 */
public final class RitualEventHandler {
    private static final int CHECK_INTERVAL = 10;
    private static final double SEARCH_RADIUS = 16.0;

    private RitualEventHandler() {}

    public static void init() {
        TickEvent.SERVER_LEVEL_POST.register(RitualEventHandler::onServerLevelTick);
    }

    private static void onServerLevelTick(ServerLevel level) {
        if (level.getGameTime() % CHECK_INTERVAL != 0) return;

        Set<BlockPos> handled = new HashSet<>();
        for (Player player : level.players()) {
            if (player.isSpectator()) continue;
            List<ItemEntity> shards = level.getEntitiesOfClass(ItemEntity.class,
                    player.getBoundingBox().inflate(SEARCH_RADIUS),
                    e -> e.isAlive() && e.getItem().is(ItemRegistry.CURSED_MOONSHARD.get()));

            for (ItemEntity shard : shards) {
                BlockPos pos = shard.blockPosition();
                if (handled.add(pos.immutable())) {
                    tryRitual(level, pos);
                }
            }
        }
    }

    private static void tryRitual(ServerLevel level, BlockPos pos) {
        BlockState cauldron = level.getBlockState(pos);
        if (!cauldron.is(Blocks.WATER_CAULDRON) || !isHeatSource(level.getBlockState(pos.below()))) return;

        AABB inside = new AABB(pos);
        ItemEntity shard = firstItem(level, inside, ItemRegistry.CURSED_MOONSHARD.get().getDefaultInstance());
        ItemEntity stone = firstItem(level, inside, ItemRegistry.PURE_STONE.get().getDefaultInstance());
        if (shard == null || stone == null) return;

        consumeOne(shard);
        consumeOne(stone);
        LayeredCauldronBlock.lowerFillLevel(cauldron, level, pos);

        double x = pos.getX() + 0.5, y = pos.getY() + 1.0, z = pos.getZ() + 0.5;
        level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.2F);
        level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.8F, 0.9F);
        level.sendParticles(ParticleTypes.WITCH, x, y, z, 10, 0.3, 0.3, 0.3, 0.02);
        level.sendParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 20, 0.3, 0.3, 0.3, 0.05);

        ItemEntity gem = new ItemEntity(level, x, y + 0.3, z, new ItemStack(ItemRegistry.NOBLE_GEM.get()));
        gem.setPickUpDelay(20);
        level.addFreshEntity(gem);
    }

    private static boolean isHeatSource(BlockState state) {
        return state.is(Blocks.FIRE) || state.is(Blocks.SOUL_FIRE) || state.is(Blocks.LAVA)
                || state.is(Blocks.MAGMA_BLOCK) || CampfireBlock.isLitCampfire(state);
    }

    private static ItemEntity firstItem(ServerLevel level, AABB box, ItemStack match) {
        List<ItemEntity> found = level.getEntitiesOfClass(ItemEntity.class, box,
                e -> e.isAlive() && ItemStack.isSameItem(e.getItem(), match));
        return found.isEmpty() ? null : found.get(0);
    }

    /** Takes a single item instead of deleting the whole stack that was thrown in. */
    private static void consumeOne(ItemEntity entity) {
        ItemStack stack = entity.getItem().copy();
        stack.shrink(1);
        if (stack.isEmpty()) {
            entity.discard();
        } else {
            entity.setItem(stack);
        }
    }
}
