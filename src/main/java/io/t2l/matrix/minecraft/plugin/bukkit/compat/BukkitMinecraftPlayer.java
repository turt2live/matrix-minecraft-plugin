package io.t2l.matrix.minecraft.plugin.bukkit.compat;

import io.t2l.matrix.minecraft.compat.MinecraftPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Bukkit version of a Minecraft Player for convenience
 */
public class BukkitMinecraftPlayer extends MinecraftPlayer {

    /**
     * Creates a new Minecraft Player from an Offline Player
     *
     * @param player the offline player
     */
    public BukkitMinecraftPlayer(OfflinePlayer player) {
        super(player.getUniqueId(), player.getName());
    }

    /**
     * Creates a new Minecraft Player from an Online Player
     *
     * @param player the player
     */
    public BukkitMinecraftPlayer(Player player) {
        super(player.getUniqueId(), player.getName(), player.getDisplayName());
    }
}
