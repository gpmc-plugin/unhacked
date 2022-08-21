package me.pythontest.unhacked.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.pythontest.unhacked.TestMechanizm.StorageManager;
import me.pythontest.unhacked.Testing.AntyKillAura;
import me.pythontest.unhacked.Testing.Levitation;
import me.pythontest.unhacked.Testing.UnnaturalPlacement;
import me.pythontest.unhacked.detection.OnConfidence;
import me.pythontest.unhacked.unhacked.Unhacked;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataType;

public class EventsListiner implements Listener {
    private OnConfidence removemamanger = new OnConfidence();
    private Unhacked plugin;

    public EventsListiner(Unhacked plugin){
        this.plugin=plugin;
    }
    /*
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
 */
    @EventHandler
    public void blockPlacment(BlockPlaceEvent e){
        UnnaturalPlacement unnaturalPlacement = new UnnaturalPlacement(e,plugin);
        unnaturalPlacement.test();
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        plugin.getStorageManager().addPlayer(e.getPlayer().getUniqueId().toString());
    }
    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent e){
        plugin.getStorageManager().removePlayer(e.getPlayer().getUniqueId().toString());
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Levitation levitation = new Levitation(e,plugin);
        levitation.test();
    }
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e){
        if(e.getEntityType()!= EntityType.PLAYER&&e.getEntity() instanceof LivingEntity){
            LivingEntity entity= (LivingEntity) e.getEntity();
            NamespacedKey health = new NamespacedKey("unhacked","entityhealth");
            entity.getPersistentDataContainer().set(health, PersistentDataType.DOUBLE,entity.getHealth());
            entity.setHealth(0.5);
        }
    }
    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getEntityType()!= EntityType.PLAYER&&e.getEntity() instanceof LivingEntity){
            LivingEntity entity= (LivingEntity) e.getEntity();
            NamespacedKey healthNamespace = new NamespacedKey("unhacked","entityhealth");
            Double health = entity.getPersistentDataContainer().getOrDefault(healthNamespace,PersistentDataType.DOUBLE,Double.valueOf(-1));
            if(health>0){
                if(health>e.getDamage()){
                    health-=e.getDamage();
                    entity.getPersistentDataContainer().set(healthNamespace, PersistentDataType.DOUBLE,health);
                    e.setDamage(0.0);
                }
                else
                    e.setDamage(100);
            }
        }
    }
    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void onEntityHealthRegen(EntityRegainHealthEvent e){
        if(e.getEntityType()!= EntityType.PLAYER&&e.getEntity() instanceof LivingEntity){
            LivingEntity entity= (LivingEntity) e.getEntity();
            NamespacedKey healthNamespace = new NamespacedKey("unhacked","entityhealth");
            Double health = entity.getPersistentDataContainer().getOrDefault(healthNamespace,PersistentDataType.DOUBLE,Double.valueOf(-1));
            if(health>0){
                health+=e.getAmount();
                if(health>entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()){
                    health=entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    entity.getPersistentDataContainer().set(healthNamespace, PersistentDataType.DOUBLE,health);
                    e.setAmount(0.0);
                }
            }
        }
    }
}
