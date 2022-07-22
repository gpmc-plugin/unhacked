package me.pythontest.unhacked.detection;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class OnConfidence {
    public boolean RemoveFromServer(Player player){
        TextComponent reason = Component.empty();
        reason= reason.content("[Unhacked] Cheating Detected");
        reason = reason.color(TextColor.fromHexString("#367ee3"));
        if(!player.hasPermission("unhacked.bypass"))
            player.kick(reason);
        else
            return false;
        return true;
    }
}
