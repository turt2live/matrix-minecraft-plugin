package io.t2l.matrix.minecraft.matrix;

import io.t2l.events.EventEmitter;
import io.t2l.matrix.minecraft.compat.ChatMessage;
import io.t2l.matrix.minecraft.compat.MinecraftPlayer;

import java.util.Collection;

/**
 * Represents a Room on Matrix.<br>
 * Should emit the following events:
 * <ul>
 *     <li>"message" - MessageEvent</li>
 *     <li>"emote" - EmoteEvent</li>
 * </ul>
 * @see io.t2l.matrix.minecraft.matrix.event.MessageEvent
 * @see io.t2l.matrix.minecraft.matrix.event.EmoteEvent
 */
public interface MatrixRoom extends EventEmitter {
    /**
     * Gets the matrix room ID. For example: "!somestringhere:matrix.org"
     *
     * @return the matrix room's ID
     */
    String getId();

    /**
     * Gets the accessible alias or ID to access the room. This may be a matrix alias
     * or a room ID, depending on the room's configuration.
     *
     * @return an alias or room ID for the matrix room
     */
    String getFriendlyAccessor();

    /**
     * Sends a message to the matrix room as a given player. The player will be
     * added to the room if they are not already in it.
     *
     * @param player  the player that sent the message; cannot be null
     * @param message the message to send; cannot be null
     */
    void sendMessage(MinecraftPlayer player, ChatMessage message);

    /**
     * Sets the players to be considered members of the matrix room. The bridge will synchronize the membership
     * list to be these players. A null or empty collection will remove all members.
     *
     * @param players the players that are intended to be participating in the room
     */
    void setPlayers(Collection<MinecraftPlayer> players);

    /**
     * Adds a player to the membership list, if they have not already been added
     *
     * @param player the player to add to the room; cannot be null
     */
    void addPlayer(MinecraftPlayer player);

    /**
     * Removes a player from the membership list, if they have not already been removed
     *
     * @param player the player to remove from the room; cannot be null
     */
    void removePlayer(MinecraftPlayer player);
}
