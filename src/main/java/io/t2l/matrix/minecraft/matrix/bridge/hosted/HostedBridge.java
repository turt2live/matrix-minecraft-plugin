package io.t2l.matrix.minecraft.matrix.bridge.hosted;

import io.t2l.matrix.minecraft.matrix.MatrixBridge;
import io.t2l.matrix.minecraft.matrix.MatrixRoomCollection;

import java.util.HashMap;
import java.util.Map;

/**
 * A bridge that uses a hosted appservice on a homeserver to bridge the chat.
 */
public class HostedBridge implements MatrixBridge {

    private MatrixRoomCollection globalChat;
    private Map<String, MatrixRoomCollection> roomMap = new HashMap<>();

    public HostedBridge(String connectionUrl, String token) {
        // TODO: Init websocket and build bridge
        // TODO: Automatically bridge global chat
    }

    @Override
    public MatrixRoomCollection bridgeGlobalChat() {
        return null;
    }

    @Override
    public MatrixRoomCollection bridgeChannel(String channelId) {
        return null;
    }
}
