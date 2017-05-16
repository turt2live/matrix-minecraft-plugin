package io.t2l.matrix.minecraft.matrix.bridge.hosted;

import io.t2l.matrix.minecraft.compat.ChatMessage;
import io.t2l.matrix.minecraft.compat.MinecraftPlayer;
import io.t2l.matrix.minecraft.matrix.MatrixRoomBase;

import java.util.ArrayList;
import java.util.Collection;

class HostedRoom extends MatrixRoomBase {

    private HostedBridge bridge;

    HostedRoom(HostedBridge bridge, String roomId) {
        super(roomId);
        this.bridge = bridge;
    }

    HostedRoom(HostedBridge bridge, String roomId, String alias) {
        super(roomId, alias);
        this.bridge = bridge;
    }

    @Override
    public void sendMessage(MinecraftPlayer player, ChatMessage message) {
        if (player == null) throw new IllegalArgumentException("player cannot be null");
        if (message == null) throw new IllegalArgumentException("message cannot be null");

        this.bridge.sendMessage(this, player, message);
    }

    @Override
    public void setPlayers(Collection<MinecraftPlayer> players) {
        if (players == null) players = new ArrayList<>();

        this.bridge.setMembers(this, players);
    }

    @Override
    public void addPlayer(MinecraftPlayer player) {
        if (player == null) throw new IllegalArgumentException("player cannot be null");

        this.bridge.addMember(this, player);
    }

    @Override
    public void removePlayer(MinecraftPlayer player) {
        if (player == null) throw new IllegalArgumentException("player cannot be null");

        this.bridge.removeMember(this, player);
    }
}
