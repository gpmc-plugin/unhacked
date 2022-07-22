package me.pythontest.unhacked.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UnHackedComplete implements TabExecutor {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> complete = new ArrayList<String>();
        if(args.length==1){
            if(sender.hasPermission("unhacked.remove"))
                complete.add("remove");

        }
        else{
            for (Player onlinePlayer : sender.getServer().getOnlinePlayers()) {
                if(!onlinePlayer.isOp()){
                    complete.add(onlinePlayer.getName());
                }
            }
        }
        return complete;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        UnHackedCommands commandsManager = new UnHackedCommands();
        return commandsManager.onCommand(sender,command,label,args);
    }
}
