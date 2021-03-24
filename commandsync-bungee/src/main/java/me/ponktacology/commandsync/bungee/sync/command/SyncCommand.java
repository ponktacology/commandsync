package me.ponktacology.commandsync.bungee.sync.command;

import me.ponktacology.commandsync.bungee.util.ColorUtil;
import me.ponktacology.commandsync.bungee.CommandSyncPlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class SyncCommand extends Command {

    private final CommandSyncPlugin syncPlugin;

    public SyncCommand(CommandSyncPlugin syncPlugin) {
        super("syncb", "command.sync");
        this.syncPlugin = syncPlugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ColorUtil.color("&cUsage: /sync <server-id> <console-cmd>"));
            return;
        }

        String channel = args[0];
        String[] commmand = new String[args.length - 1];

        System.arraycopy(args, 1, commmand, 0, commmand.length);

        ProxyServer.getInstance().getScheduler().runAsync(syncPlugin, () -> syncPlugin.getSyncManager().sendCommand(String.join(" ", commmand), channel));
        sender.sendMessage(ColorUtil.color("&aSuccessfully executed command."));
    }
}
