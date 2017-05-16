package io.t2l.matrix.minecraft.plugin.bukkit;

import io.t2l.matrix.minecraft.matrix.MatrixBridge;
import io.t2l.matrix.minecraft.matrix.bridge.hosted.HostedBridge;
import net.milkbowl.vault.chat.Chat;
import org.bstats.Metrics;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Bukkit entry point for Matrix Minecraft chat bridge
 */
public class BukkitPlugin extends JavaPlugin {

    private static final Logger log = Logger.getLogger(BukkitPlugin.class.getName());
    private Chat chat = null;
    private MatrixBridge bridge;

    @Override
    public void onEnable() {
        if (!setupChat()) {
            log.info("No chat plugin found, using vanilla setup");
            // TODO: Bridge all chat
        } else {
            log.info("Chat plugin found. Attempting to bridge groups");
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
        log.info("Setting up metrics...");
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
