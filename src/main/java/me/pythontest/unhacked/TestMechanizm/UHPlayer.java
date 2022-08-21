package me.pythontest.unhacked.TestMechanizm;

import me.pythontest.unhacked.Statics.ChatMessages;
import me.pythontest.unhacked.detection.OnConfidence;
import me.pythontest.unhacked.unhacked.Unhacked;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.*;

public class UHPlayer {
    private Unhacked plugin;
    private Player player;
    private Map<String, Integer> testes = new HashMap<>();
    public Long lastBlockPlace = null;
    public List<Double> lastBPS = new ArrayList<>();

    public UHPlayer(String uuid, Unhacked plugin) {
        this.plugin = plugin;
        player = plugin.getServer().getPlayer(UUID.fromString(uuid));
    }

    public void addTest(String testID, Component moreInfo) {
        /*
        if (player.hasPermission("unhacked.bypass"))
            return;
            */
        Integer times = testes.getOrDefault(testID, 0);
        if (times == 0)
            testes.put(testID, 1);
        else
            testes.replace(testID, times + 1);
        times++;
        Integer maxTimes = new TestMaxStatic().MaxConfidensTimes.getOrDefault(testID, 2137);
        if (maxTimes <= times) {
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                if(onlinePlayer.hasPermission("unhacked.notification"))
                    onlinePlayer.sendMessage(ChatMessages.testFailed(player.getName(), testID, moreInfo.append(Component.text("\nPlaying from: " + player.getClientBrandName())), times, maxTimes));

            }
            plugin.getServer().getConsoleSender().sendMessage(ChatMessages.testFailedConsle(player.getName(), testID, moreInfo.append(Component.text("\nPlaying from: " + player.getClientBrandName())), times, maxTimes));

            new OnConfidence().RemoveFromServer(player);
        }
        else {
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                if(onlinePlayer.hasPermission("unhacked.notification"))
                    onlinePlayer.sendMessage(ChatMessages.testFailed(player.getName(), testID, moreInfo.append(Component.text("\nPlaying from: " + player.getClientBrandName())), times, maxTimes));

            }
            plugin.getServer().getConsoleSender().sendMessage(ChatMessages.testFailedConsle(player.getName(), testID, moreInfo.append(Component.text("\nPlaying from: " + player.getClientBrandName())), times, maxTimes));
        }
    }
}
