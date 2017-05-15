package io.t2l.matrix.minecraft.matrix.event;

import io.t2l.matrix.minecraft.matrix.MatrixRoom;
import io.t2l.matrix.minecraft.matrix.MatrixUser;

/**
 * Fired when an emote has been received by an actor
 */
public class EmoteEvent extends MessageEvent {

    /**
     * Creates a new emote event without an HTML component
     *
     * @param room         the room the message was sent in
     * @param sender       the sender who sent the message
     * @param plainMessage the text of the message, cannot be null or whitespace
     */
    public EmoteEvent(MatrixRoom room, MatrixUser sender, String plainMessage) {
        super(room, sender, plainMessage);
    }

    /**
     * Creates a new emote event with an HTML component
     *
     * @param room         the room the message was sent in
     * @param sender       the sender who sent the message
     * @param plainMessage the text of the message, cannot be null or whitespace
     * @param htmlMessage  the HTML of the message, optional
     */
    public EmoteEvent(MatrixRoom room, MatrixUser sender, String plainMessage, String htmlMessage) {
        super(room, sender, plainMessage, htmlMessage);
    }
}
