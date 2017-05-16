package io.t2l.matrix.minecraft.integration;

import io.t2l.matrix.minecraft.compat.ChatMessage;
import io.t2l.matrix.minecraft.compat.MinecraftPlayer;
import io.t2l.matrix.minecraft.matrix.MatrixBridge;
import io.t2l.matrix.minecraft.matrix.bridge.hosted.HostedBridge;

import java.util.UUID;

/* ************************************************
 * All integration tests require manual review to *
 * ensure accuracy of behaviour.                  *
 ************************************************ */

public class HostedBridgeIntegrationTest {
    public static void main(String[] args) {
        String connectionUrl = "http://localhost:8082";
        MatrixBridge bridge = new HostedBridge(connectionUrl, "assumed_token");
        bridge.bridgeGlobalChat().sendMessage(new MinecraftPlayer(UUID.randomUUID(), "turt2live"), new ChatMessage() {
            @Override
            public String asText() {
                return "Hello World!";
            }

            @Override
            public String asHtml() {
                return "<strong>Hello</strong> <i>World!</i>";
            }
        });
    }
}
