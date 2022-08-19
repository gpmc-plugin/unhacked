package me.pythontest.unhacked.Testing;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class Levitation {
    PlayerMoveEvent event;
    public Levitation(PlayerMoveEvent e){
        this.event = e;
    }
    public boolean test(){
        Player player  = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        return false;
    }
}
