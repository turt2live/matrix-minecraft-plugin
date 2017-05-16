package io.t2l.matrix.minecraft.plugin.bukkit;

import io.t2l.matrix.minecraft.compat.MinecraftPlayer;
import io.t2l.matrix.minecraft.matrix.MatrixBridge;
import io.t2l.matrix.minecraft.plugin.bukkit.compat.BukkitMinecraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Chat handler for Bukkit-based servers
 */
public class ChatListener implements Listener {

    private MatrixBridge bridge;

    public ChatListener(MatrixBridge bridge) {
        if(bridge == null)throw new IllegalArgumentException("bridge cannot be null");

        this.bridge = bridge;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChatMessage(AsyncPlayerChatEvent event) {
        MinecraftPlayer player = new BukkitMinecraftPlayer(event.getPlayer());

    }

}
