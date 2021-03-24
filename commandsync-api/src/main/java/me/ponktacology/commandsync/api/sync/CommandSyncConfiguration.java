package me.ponktacology.commandsync.api.sync;

import lombok.Getter;
import me.ponktacology.commandsync.api.configuration.Configuration;

import java.util.Arrays;
import java.util.List;

@Getter
public class CommandSyncConfiguration implements Configuration {

    private String redisHost = "localhost";
    private int redisPort = 6379;
    private String redisPassword = "";
    private List<String> channels = Arrays.asList("group1", "skyblock");
}
