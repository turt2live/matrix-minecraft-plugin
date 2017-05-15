package io.t2l.matrix.minecraft.matrix.event;

import io.t2l.matrix.minecraft.matrix.MatrixRoom;
import io.t2l.matrix.minecraft.matrix.MatrixUser;

/**
 * Fired when a message has been received from an actor
 */
public class MessageEvent {
    private MatrixUser sender;
    private MatrixRoom room;
    private String htmlMessage;
    private String plainMessage;

    /**
     * Creates a new message event with no HTML component
     *
     * @param room         the room the message was sent in
     * @param sender         the sender who sent the message
     * @param plainMessage the text of the message, cannot be null or whitespace
     */
    public MessageEvent(MatrixRoom room, MatrixUser sender, String plainMessage) {
        if (room == null) throw new IllegalArgumentException("room cannot be null");
        if (sender == null) throw new IllegalArgumentException("sender cannot be null");
        if (plainMessage == null || plainMessage.trim().length() == 0)
            throw new IllegalArgumentException("message cannot be null or whitespace");

        this.room = room;
        this.sender = sender;
        this.plainMessage = plainMessage;
    }

    /**
     * Creates a new message event with an HTML component
     *
     * @param room         the room the message was sent in
     * @param sender         the sender who sent the message
     * @param plainMessage the text of the message, cannot be null or whitespace
     * @param htmlMessage  the HTML version of the message, optional
     */
    public MessageEvent(MatrixRoom room, MatrixUser sender, String plainMessage, String htmlMessage) {
        this(room, sender, plainMessage);
        this.htmlMessage = htmlMessage;
    }

    /**
     * Gets the sender of this message
     *
     * @return the sender
     */
    public MatrixUser getSender() {
        return this.sender;
    }

    /**
     * Gets the room the message was sent in
     *
     * @return the room
     */
    public MatrixRoom getRoom() {
        return this.room;
    }

    /**
     * Gets the message sent (plain text)
     *
     * @return the message
     */
    public String getMessage() {
        return this.plainMessage;
    }

    /**
     * Gets the HTML message sent, if provided
     *
     * @return the HTML message; may be null or whitespace
     */
    public String getHtmlMessage() {
        return this.htmlMessage;
    }
}
