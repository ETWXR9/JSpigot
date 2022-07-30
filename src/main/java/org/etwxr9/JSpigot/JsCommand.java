package org.etwxr9.JSpigot;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class JsCommand extends Command implements PluginIdentifiableCommand {
    CommandSender sender;//So you can mess with this inside this class
    JSpigot plugin = JSpigot.getInstance();
    protected JsCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] arguments) {
        this.sender = sender;
        run(sender, commandLabel, arguments);
        return true;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    public abstract void run(CommandSender sender, String commandLabel, String[] arguments);
    public abstract List<String> tabComplete(CommandSender sender, String commandLabel, String[] args);
}
