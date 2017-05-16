package io.t2l.matrix.minecraft.matrix;

import io.t2l.events.EventEmitterImpl;

/**
 * Represents the basics for a Matrix Room
 */
public abstract class MatrixRoomBase extends EventEmitterImpl implements MatrixRoom {

    private String roomId;
    private String alias;

    /**
     * Creates a new Matrix Room Base
     *
     * @param roomId the room ID for the room, cannot be null or whitespace
     */
    protected MatrixRoomBase(String roomId) {
        if (roomId == null || roomId.trim().length() == 0)
            throw new IllegalArgumentException("room ID cannot be null or whitespace");
        this.roomId = roomId;
    }

    /**
     * Creates a new Matrix Room Base
     *
     * @param roomId the room ID for the room, cannot be null or whitespace
     * @param alias  the alias for the room, optional
     */
    protected MatrixRoomBase(String roomId, String alias) {
        this(roomId);

        this.alias = alias;
    }

    @Override
    public String getFriendlyAccessor() {
        return this.alias != null && this.alias.trim().length() > 0 ? this.alias : this.roomId;
    }

    @Override
    public String getId() {
        return this.roomId;
    }
}
