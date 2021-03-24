package me.ponktacology.commandsync.bukkit;

import lombok.Getter;
import me.ponktacology.commandsync.api.configuration.ConfigurationFactory;
import me.ponktacology.commandsync.api.sync.SyncManager;
import me.ponktacology.commandsync.api.sync.CommandSyncConfiguration;
import me.ponktacology.commandsync.bukkit.sync.BukkitSyncManager;
import me.ponktacology.commandsync.bukkit.sync.command.SyncCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandSyncPlugin extends JavaPlugin {

    private static final CommandSyncConfiguration CONFIGURATION = new ConfigurationFactory("CommandSync/config.json").load(CommandSyncConfiguration.class);

    @Getter
    private final SyncManager syncManager = new BukkitSyncManager(this, CONFIGURATION.getRedisHost(), CONFIGURATION.getRedisPort(), CONFIGURATION.getRedisPassword(), CONFIGURATION.getChannels().toArray(new String[0]));

    @Override
    public void onEnable() {
        getCommand("sync").setExecutor(new SyncCommand(this));
    }
}
