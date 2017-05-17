package io.t2l.matrix.minecraft.plugin.bukkit;

import io.t2l.matrix.minecraft.matrix.MatrixBridge;
import io.t2l.matrix.minecraft.matrix.bridge.hosted.HostedBridge;
import net.milkbowl.vault.chat.Chat;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {

    private Chat chat = null;
    private MatrixBridge bridge;

    @Override
    public void onEnable() {
        if (!setupChat()) {
            getLogger().info("No chat plugin found, using vanilla setup");
            // TODO: Bridge all chat
        } else {
            getLogger().info("Chat plugin found. Attempting to bridge groups");
            // TODO: Bridge groups
        }

        setupMetrics();
        setupConfiguration();
        setupBridge();

        this.getServer().getPluginManager().registerEvents(new ChatListener(this.bridge), this);
    }

    private void setupBridge() {
        switch (this.getConfig().getString("bridgeType")) {
            case "hosted":
                this.bridge = new HostedBridge(this.getConfig().getString("hostedBridge.connectionUrl"), this.getConfig().getString("hostedBridge.token"));
                return;
            default:
                throw new IllegalArgumentException("Unknown bridge type: " + this.getConfig().getString("bridgeType"));
        }
    }

    private void setupConfiguration() {
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
    }

    private void setupMetrics() {
        getLogger().info("Setting up metrics...");
        Metrics metrics = new Metrics(this);

        metrics.addCustomChart(new Metrics.SimplePie("chat_plugin") {
            @Override
            public String getValue() {
                return chat != null ? chat.getName() : "None";
            }
        });
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
        this.chat = rsp.getProvider();
        return this.chat != null;
    }

    public Chat getChat() {
        return this.chat;
    }

    public MatrixBridge getBridge() {
        return this.bridge;
    }
}
