package me.pythontest.unhacked.commands;

import org.bukkit.plugin.java.JavaPlugin;

public class SetupCommands {
    public static void SetupCommands(JavaPlugin plugin){
        plugin.getCommand("unhacked").setTabCompleter(new UnHackedComplete());
        plugin.getCommand("unhacked").setExecutor(new UnHackedCommands());

    }
}
