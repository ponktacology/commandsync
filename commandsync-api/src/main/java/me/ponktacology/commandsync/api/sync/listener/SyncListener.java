package me.ponktacology.commandsync.api.sync.listener;

import lombok.RequiredArgsConstructor;
import me.ponktacology.commandsync.api.sync.SyncManager;
import redis.clients.jedis.JedisPubSub;

@RequiredArgsConstructor
public class SyncListener extends JedisPubSub {

    private final SyncManager commandSyncManager;

    @Override
    public void onMessage(String channel, String message) {
        commandSyncManager.executeCommand(message);
    }
}
