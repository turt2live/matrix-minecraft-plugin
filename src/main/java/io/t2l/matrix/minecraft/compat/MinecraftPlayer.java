package io.t2l.matrix.minecraft.compat;

import java.util.UUID;

/**
 * Represents a Minecraft Player (a UUID, Name, and Display Name if provided)
 */
public class MinecraftPlayer {

    private String name;
    private String displayName;
    private UUID uuid;

    /**
     * Creates a new Minecraft player that doesn't have a display name
     *
     * @param uuid the uuid of the player, cannot be null
     * @param name the name of the player, cannot be null or empty
     */
    public MinecraftPlayer(UUID uuid, String name) {
        if (uuid == null) throw new IllegalArgumentException("uuid cannot be null");
        if (name == null || name.trim().length() == 0)
            throw new IllegalArgumentException("name cannot be null or empty");

        this.uuid = uuid;
        this.name = name;
    }

    /**
     * Creates a new Minecraft player that has a display name
     *
     * @param uuid        the uuid of the player, cannot be null
     * @param name        the name of the player, cannot be null or empty
     * @param displayName the display name of the player, optional
     */
    public MinecraftPlayer(UUID uuid, String name, String displayName) {
        this(uuid, name);

        this.displayName = displayName;
    }

    /**
     * Gets the name of the player
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the display name of the player, if provided
     *
     * @return the display name of the player
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the UUID of the player
     *
     * @return the UUID of the player
     */
    public UUID getUuid() {
        return uuid;
    }
}
