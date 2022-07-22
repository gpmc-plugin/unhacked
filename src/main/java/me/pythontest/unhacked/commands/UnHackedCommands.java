package me.pythontest.unhacked.commands;

import me.pythontest.unhacked.detection.OnConfidence;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UnHackedCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.getServer().getConsoleSender().sendMessage(args[0]);
        if(args.length<2)
            sender.sendMessage(Component.empty().content("Wrong arguments").color(TextColor.fromHexString("#8b0000")));

        else
            if(args[0].equals("remove"))
                return onRemove(sender,command,label,args);
        sender.sendMessage("Can't find module");
        return false;
    }
    private boolean onRemove(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if(!sender.hasPermission("unhacked.remove")){
            sender.sendMessage("Unmet Predictions");
            return false;
        }
        OnConfidence confidenceManager = new OnConfidence();
        if(sender.getServer().getPlayer(args[1]) instanceof Player){
            Player target = sender.getServer().getPlayer(args[1]);
            if(confidenceManager.RemoveFromServer(target)){
                sender.sendMessage("Removed");
                return true;
            }
            else
                sender.sendMessage("Cannot remove "+target.getName());

        }
        else
            sender.sendMessage("Provided wrong username");
        return false;
    }
}
