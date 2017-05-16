package io.t2l.matrix.minecraft.matrix.bridge.hosted;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.t2l.matrix.minecraft.compat.ChatMessage;
import io.t2l.matrix.minecraft.compat.MinecraftPlayer;
import io.t2l.matrix.minecraft.matrix.MatrixBridge;
import io.t2l.matrix.minecraft.matrix.MatrixRoom;
import io.t2l.matrix.minecraft.matrix.MatrixRoomCollection;
import org.json.JSONArray;
import org.json.JSONException;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * A bridge that uses a hosted appservice on a homeserver to bridge the chat.
 */
public class HostedBridge implements MatrixBridge {

    private MatrixRoomCollection globalChat;
    private Map<String, MatrixRoomCollection> roomMap = new HashMap<>();
    private Socket socket;

    public HostedBridge(String connectionUrl, String token) {
        IO.Options options = new IO.Options();
        options.forceNew = true;
        options.reconnection = true;
        options.reconnectionAttempts = -1; // forever
        options.query = "auth=" + token;

        try {
            this.socket = IO.socket(connectionUrl, options);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        this.socket.connect();
    }

    @Override
    public MatrixRoomCollection bridgeGlobalChat() {
        if (globalChat == null) {
            globalChat = this.requestBridge("MC|Global");
        }
        return globalChat;
    }

    @Override
    public MatrixRoomCollection bridgeChannel(String channelId) {
        if (this.roomMap.containsKey(channelId))
            return this.roomMap.get(channelId);

        MatrixRoomCollection collection = this.requestBridge(channelId);
        this.roomMap.put(channelId, collection);

        return collection;
    }

    private MatrixRoomCollection requestBridge(String channelId) {
        Object[] result = this.emitSync("requestRoom", channelId);
        JSONArray roomIds = (JSONArray) result[0];
        MatrixRoom[] rooms = new MatrixRoom[roomIds.length()];
        for (int i = 0; i < roomIds.length(); i++) {
            try {
                rooms[i] = new HostedRoom(this, roomIds.getString(i));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return new MatrixRoomCollection(rooms);
    }

    void sendMessage(MatrixRoom room, MinecraftPlayer sender, ChatMessage message) {
        this.socket.emit("message", new HashMap<String, Object>() {{
            put("roomId", room.getId());
            put("playerId", sender.getUuid());
            put("playerName", sender.getName());
            put("playerDisplayName", sender.getDisplayName());
            put("messageText", message.asText());
            put("messageHtml", message.asHtml());
        }});
    }

    void setMembers(MatrixRoom room, Collection<MinecraftPlayer> players) {
        this.socket.emit("memberList", players.stream().map(p -> new HashMap<String, Object>() {{
            put("roomId", room.getId());
            put("playerId", p.getUuid());
            put("playerName", p.getName());
            put("playerDisplayName", p.getDisplayName());
        }}));
    }

    void addMember(MatrixRoom room, MinecraftPlayer player) {
        this.socket.emit("memberAdd", new HashMap<String, Object>() {{
            put("roomId", room.getId());
            put("playerId", player.getUuid());
            put("playerName", player.getName());
            put("playerDisplayName", player.getDisplayName());
        }});
    }

    void removeMember(MatrixRoom room, MinecraftPlayer player) {
        this.socket.emit("memberRemove", new HashMap<String, Object>() {{
            put("roomId", room.getId());
            put("playerId", player.getUuid());
            put("playerName", player.getName());
            put("playerDisplayName", player.getDisplayName());
        }});
    }

    private Object[] emitSync(String event, Object... payload) {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            final Object[][] result = {null};

            String callbackId = "callback-" + System.currentTimeMillis();

            this.socket.once(callbackId, objs -> {
                result[0] = objs;
                latch.countDown();
            });
            this.socket.emit(event, callbackId, payload);

            latch.await();
            return result[0];
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
