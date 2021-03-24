package me.ponktacology.commandsync.bungee.sync;

import me.ponktacology.commandsync.api.sync.SyncManager;
import net.md_5.bungee.api.ProxyServer;

public class BungeeSyncManager extends SyncManager {

    public BungeeSyncManager(String host, int port, String password, String... channels) {
        super(host, port, password, channels);
    }

    @Override
    public void executeCommand(String command) {
        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
    }
}
