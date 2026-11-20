package net.pixeldreamstudios.gearsofvalor.util;

import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Transient per-player combat state (server side only). Kept off the ItemStack on purpose: writing data components to
 * worn armor every few ticks counts as an equipment change, which replays the equip sound and resyncs the item.
 */
public final class PlayerState {
    private static final Map<UUID, PlayerState> STATES = new ConcurrentHashMap<>();

    // Arismas: stillness tracking
    public double lastX, lastY, lastZ;
    public int stillTicks;

    // Woodland: Thornroot cooldown (game time)
    public long thornrootReadyAt;

    // Fury's Call: Rising Fury stacks
    public int furyStacks;
    public long furyLastHit;

    private PlayerState() {}

    public static PlayerState of(Player player) {
        return STATES.computeIfAbsent(player.getUUID(), id -> new PlayerState());
    }

    public static void clear(Player player) {
        STATES.remove(player.getUUID());
    }
}
