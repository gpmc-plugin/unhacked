package me.pythontest.unhacked.events;

import me.pythontest.unhacked.Testing.AntyKillAura;
import me.pythontest.unhacked.detection.OnConfidence;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventsListiner implements Listener {
    OnConfidence removemamanger = new OnConfidence();
    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if(e.getDamager() instanceof Player) {
                AntyKillAura antyKillAura = new AntyKillAura(e);
                if (antyKillAura.Test()) {
                    e.setCancelled(true);
                    removemamanger.RemoveFromServer((Player) e.getDamager());
                }
            }
        }
    }
}
