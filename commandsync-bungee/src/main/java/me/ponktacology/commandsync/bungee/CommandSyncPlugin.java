package me.ponktacology.commandsync.bungee;

import lombok.Getter;
import me.ponktacology.commandsync.api.configuration.ConfigurationFactory;
import me.ponktacology.commandsync.api.sync.CommandSyncConfiguration;
import me.ponktacology.commandsync.api.sync.SyncManager;
import me.ponktacology.commandsync.bungee.sync.command.SyncCommand;
import me.ponktacology.commandsync.bungee.sync.BungeeSyncManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class CommandSyncPlugin extends Plugin {

    private static final CommandSyncConfiguration CONFIGURATION = new ConfigurationFactory("CommandSync/config.json").load(CommandSyncConfiguration.class);

    @Getter
    private final SyncManager syncManager = new BungeeSyncManager(CONFIGURATION.getRedisHost(), CONFIGURATION.getRedisPort(), CONFIGURATION.getRedisPassword(), CONFIGURATION.getChannels().toArray(new String[0]));

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new SyncCommand(this));
    }
}
