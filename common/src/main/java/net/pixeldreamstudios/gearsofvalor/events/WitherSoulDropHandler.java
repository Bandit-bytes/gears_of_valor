package net.pixeldreamstudios.gearsofvalor.events;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.EventResult;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.gearsofvalor.registry.ItemRegistry;

public class WitherSoulDropHandler {
    public static void init() {
        EntityEvent.LIVING_DEATH.register((entity, source) -> {
            if (!(entity instanceof WitherBoss) || entity.level().isClientSide()) {
                return EventResult.pass();
            }

            int count = 1 + entity.getRandom().nextInt(2); // 1-2
            ServerLevel level = (ServerLevel) entity.level();
            ItemStack drop = new ItemStack(ItemRegistry.UNSEEN_SOUL.get(), count);

            ItemEntity itemEntity = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), drop);
            itemEntity.setPickUpDelay(20);
            level.addFreshEntity(itemEntity);

            return EventResult.pass();
        });
    }
}
