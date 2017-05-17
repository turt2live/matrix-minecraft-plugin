package io.t2l.matrix.minecraft.plugin.bungee;


import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

public class BungeePlugin extends Plugin {

    @Override
    public void onEnable() {
        setupMetrics();
    }

    private void setupMetrics() {
        getLogger().info("Setting up metrics...");
        Metrics metrics = new Metrics(this);

        // TODO: Custom graphs
    }
}
