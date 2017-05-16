package io.t2l.matrix.minecraft.matrix;

import io.t2l.events.EventEmitterImpl;
import io.t2l.matrix.minecraft.compat.ChatMessage;
import io.t2l.matrix.minecraft.compat.MinecraftPlayer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a collection of rooms. Useful for when multiple rooms need the same
 * action to be performed on them.<br/>
 * Emits the following events:
 * <ul>
 * <li>"message" - MessageEvent</li>
 * <li>"emote" - EmoteEvent</li>
 * </ul>
 *
 * @see io.t2l.matrix.minecraft.matrix.event.MessageEvent
 * @see io.t2l.matrix.minecraft.matrix.event.EmoteEvent
 */
public class MatrixRoomCollection extends EventEmitterImpl {

    private ArrayList<MatrixRoom> rooms = new ArrayList<>();

    /**
     * Creates a new matrix room collection
     *
     * @param rooms the rooms to be part of the collection
     */
    public MatrixRoomCollection(MatrixRoom... rooms) {
        if (rooms == null) return;
        for (MatrixRoom room : rooms) {
            this.rooms.add(room);
        }
    }

    /**
     * Creates a new matrix room collection
     *
     * @param rooms the rooms to be part of the collection
     */
    public MatrixRoomCollection(Collection<MatrixRoom> rooms) {
        if (rooms == null) return;
        this.rooms.addAll(rooms);
    }

    /**
     * Sends a message to the matrix room as a given player. The player will be
     * added to the room if they are not already in it.
     *
     * @param player  the player that sent the message
     * @param message the message to send
     * @see MatrixRoom#sendMessage(MinecraftPlayer, ChatMessage)
     */
    public void sendMessage(MinecraftPlayer player, ChatMessage message) {
        rooms.forEach(r -> r.sendMessage(player, message));
    }

    /**
     * Sets the players to be considered members of the matrix room. The bridge will synchronize the membership
     * list to be these players.
     *
     * @param players the players that are intended to be participating in the room
     * @see MatrixRoom#setPlayers(Collection)
     */
    public void setPlayers(Collection<MinecraftPlayer> players) {
        rooms.forEach(r -> r.setPlayers(players));
    }

    /**
     * Adds a player to the membership list, if they have not already been added
     *
     * @param player the player to add to the room
     * @see MatrixRoom#addPlayer(MinecraftPlayer)
     */
    public void addPlayer(MinecraftPlayer player) {
        rooms.forEach(r -> r.addPlayer(player));
    }

    /**
     * Removes a player from the membership list, if they have not already been removed
     *
     * @param player the player to remove from the room
     * @see MatrixRoom#removePlayer(MinecraftPlayer)
     */
    public void removePlayer(MinecraftPlayer player) {
        rooms.forEach(r -> r.removePlayer(player));
    }
}
