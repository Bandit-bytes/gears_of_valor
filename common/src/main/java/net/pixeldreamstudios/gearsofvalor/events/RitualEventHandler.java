package net.pixeldreamstudios.gearsofvalor.events;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

import java.util.List;

public class RitualEventHandler {

    public static void init() {
        TickEvent.SERVER_LEVEL_POST.register(RitualEventHandler::onServerLevelTick);
    }

    private static void onServerLevelTick(ServerLevel level) {
        level.players().forEach(player -> {
            BlockPos center = player.blockPosition();
            BlockPos min = center.offset(-8, -2, -8);
            BlockPos max = center.offset(8, 2, 8);

            for (BlockPos pos : BlockPos.betweenClosed(min, max)) {
                BlockState state = level.getBlockState(pos);
                if (!state.is(Blocks.WATER_CAULDRON)) continue;

                BlockState beneath = level.getBlockState(pos.below());
                boolean fireBelow = beneath.is(Blocks.FIRE) || beneath.is(Blocks.SOUL_FIRE)
                        || beneath.is(Blocks.CAMPFIRE) || beneath.is(Blocks.SOUL_CAMPFIRE)
                        || beneath.is(Blocks.LAVA);
                if (!fireBelow) continue;

                List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class,
                        new AABB(pos).inflate(0.5));
                ItemEntity moonshard = null;
                ItemEntity purifier = null;

                for (ItemEntity item : items) {
                    ItemStack stack = item.getItem();
                    if (stack.is(ItemRegistry.CURSED_MOONSHARD.get())) moonshard = item;
                    else if (stack.is(ItemRegistry.PURE_STONE.get())) purifier = item;
                }

                if (moonshard != null && purifier != null) {
                    moonshard.discard();
                    purifier.discard();

                    level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0f, 1.2f);
                    level.sendParticles(ParticleTypes.WITCH,
                            pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                            10, 0.3, 0.3, 0.3, 0.02);
                    level.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                            pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                            20, 0.3, 0.3, 0.3, 0.05);

                    ItemEntity gem = new ItemEntity(level,
                            pos.getX() + 0.5, pos.getY() + 1.3, pos.getZ() + 0.5,
                            new ItemStack(ItemRegistry.NOBLE_GEM.get()));
                    gem.setPickUpDelay(20);
                    level.addFreshEntity(gem);
                }
            }
        });
    }
}
