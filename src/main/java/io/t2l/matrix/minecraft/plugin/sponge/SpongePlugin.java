package io.t2l.matrix.minecraft.plugin.sponge;


import com.google.inject.Inject;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "matrix-bridge") // other parameters are set in mcmod.info
public class SpongePlugin {

    @Inject @ConfigDir(sharedRoot = false) private Path configDir;
    @Inject @ConfigDir(sharedRoot = true) private Path mainDir;
    @Inject private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Setting up matrix bridge...");
        setupConfiguration();
    }

    @Listener
    public void onServerStop(GameStoppingServerEvent event) {

    }

    private void setupConfiguration(){
        // TODO: Some code to actually load a default config
        // Resource: https://github.com/SpongePowered/Cookbook/blob/master/Plugin/ConfigDatabase/src/main/java/org/spongepowered/cookbook/plugin/configdatabase/ConfigDatabase.java
        // Resource: https://docs.spongepowered.org/stable/en/plugin/configuration/loaders.html#example-loading-a-default-config-from-the-plugin-jar-file
    }
}
