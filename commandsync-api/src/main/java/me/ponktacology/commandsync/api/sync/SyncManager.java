package me.ponktacology.commandsync.api.sync;

import me.ponktacology.commandsync.api.sync.listener.SyncListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;

public abstract class SyncManager {

    private final JedisPool jedisPool;

    public SyncManager(String host, int port, String password, String... channels) {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool;
        if (password.isEmpty()) {
            jedisPool = new JedisPool(poolConfig, host, port);
        } else {
            jedisPool = new JedisPool(poolConfig, host, port, Integer.MAX_VALUE, password);
        }

        this.jedisPool = jedisPool;

        new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(new SyncListener(this), channels);
            }
        }).start();
    }

    public abstract void executeCommand(String command);

    public void sendCommand(String command, String channel) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.publish(channel, command);
        }
    }


}
