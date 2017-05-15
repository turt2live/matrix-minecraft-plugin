package io.t2l.matrix.minecraft.matrix;

/**
 * Represents a bridge to and from Matrix
 */
public interface MatrixBridge {
    MatrixRoomCollection bridgeGlobalChat();
    MatrixRoomCollection bridgeChannel(String channelId);
}
