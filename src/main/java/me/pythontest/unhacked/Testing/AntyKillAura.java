package me.pythontest.unhacked.Testing;

import me.pythontest.unhacked.engines.RayCast;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class AntyKillAura {
    EntityDamageByEntityEvent e;
    final double RaycastTolerance = 0.75;
    public AntyKillAura(EntityDamageByEntityEvent event){
        this.e = event;

    }
    public boolean Test(){
        if(opentest()){
            e.getDamager().getServer().getConsoleSender().sendMessage("Failed open inventory test");
            return true;
        }

        if(raycasttest()){
            e.getDamager().getServer().getConsoleSender().sendMessage("Failed Raycast test");
            return true;
        }

        return false;
    }
    private boolean opentest(){
        if(this.e.getDamager() instanceof Player){
            Player damager = (Player) this.e.getDamager();
            e.getDamager().getServer().getConsoleSender().sendMessage(damager.getOpenInventory().getType().toString());
            if(damager.getOpenInventory().getType()!= InventoryType.CRAFTING&&!damager.getGameMode().equals(GameMode.CREATIVE))
                return true;
        }
        return false;
    }
    private boolean raycasttest(){
        if(this.e.getDamager() instanceof Player){
           Player damager = (Player) e.getDamager();
           Entity damaged = e.getEntity();
           double minwidth = Math.min(damaged.getBoundingBox().getWidthX(),damaged.getBoundingBox().getWidthZ())/2;
           double mindistance = Math.min(minwidth,damaged.getBoundingBox().getHeight());
            RayCast rayCast = new RayCast(damager.getEyeLocation(),damager.getEyeLocation().getDirection());
            BoundingBox hitbox = damaged.getBoundingBox();
            Vector mintolerance = new Vector(hitbox.getMinX()-RaycastTolerance,hitbox.getMinY()-RaycastTolerance,hitbox.getMinZ()-RaycastTolerance);
            Vector maxtolerance = new Vector(hitbox.getMaxX()+RaycastTolerance,hitbox.getMaxY()+RaycastTolerance,hitbox.getMaxZ()+RaycastTolerance);
            hitbox = new BoundingBox(mintolerance.getX(), mintolerance.getY(),mintolerance.getZ(),maxtolerance.getX(),maxtolerance.getY(),maxtolerance.getZ());
           for(double i=0;i<damager.getLocation().distance(damaged.getLocation())*2;i+=mindistance){
                Vector hitposition = rayCast.getRayPoint(i);
               Location blocklocation = new Location(damager.getWorld(),hitposition.getX(),hitposition.getY(),hitposition.getZ());
               Block hitedblock = blocklocation.getBlock();
               damager.getServer().getConsoleSender().sendMessage(damaged.getLocation().toVector().toString());
               damager.getServer().getConsoleSender().sendMessage(hitbox.getMin().toString());
               damager.getServer().getConsoleSender().sendMessage(hitposition.toString());
               damager.getServer().getConsoleSender().sendMessage(hitbox.getMax().toString());
               damager.getServer().getConsoleSender().sendMessage(hitedblock.getType().name());
               if(hitbox.contains(hitposition))
                   return false;
               if(hitedblock.getType().isSolid())
                   return true;

           }
           return true;
        }

        return false;
    }

}
