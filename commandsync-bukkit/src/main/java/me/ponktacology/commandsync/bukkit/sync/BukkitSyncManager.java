package me.ponktacology.commandsync.bukkit.sync;

import me.ponktacology.commandsync.api.sync.SyncManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitSyncManager extends SyncManager {

    private final JavaPlugin plugin;

    public BukkitSyncManager(JavaPlugin plugin, String host, int port, String password, String... channels) {
        super(host, port, password, channels);
        this.plugin = plugin;
    }

    @Override
    public void executeCommand(String command) {
        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command));
    }
}
