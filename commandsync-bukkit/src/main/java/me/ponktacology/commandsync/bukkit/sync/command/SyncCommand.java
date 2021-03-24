package me.ponktacology.commandsync.bukkit.sync.command;

import lombok.RequiredArgsConstructor;
import me.ponktacology.commandsync.bukkit.CommandSyncPlugin;
import me.ponktacology.commandsync.bukkit.utill.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class SyncCommand implements CommandExecutor {

    private final CommandSyncPlugin syncPlugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (!name.equalsIgnoreCase("sync")) return false;

        if (!sender.hasPermission("command.sync")) {
            sender.sendMessage(ColorUtil.color("&cNo permission."));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(ColorUtil.color("&cUsage: /sync <server-id> <console-cmd>"));
            return false;
        }

        String channel = args[0];
        String[] commandArgs = new String[args.length - 1];

        System.arraycopy(args, 1, commandArgs, 0, commandArgs.length);

        Bukkit.getScheduler().runTaskAsynchronously(syncPlugin, () -> syncPlugin.getSyncManager().sendCommand(String.join(" ", commandArgs), channel));
        sender.sendMessage(ColorUtil.color("&aSuccessfully executed command."));
        return true;
    }
}
