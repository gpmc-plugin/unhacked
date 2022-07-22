package me.pythontest.unhacked.Testing;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.util.RayTraceResult;

public class AntyKillAura {
    EntityDamageByEntityEvent e;
    public AntyKillAura(EntityDamageByEntityEvent event){
        this.e = event;

    }
    public boolean Test(){
        if(opentest())
            return true;
        if(raycasttest())
            return true;
        return false;
    }
    private boolean opentest(){
        if(this.e.getDamager() instanceof Player){
            Player damager = (Player) this.e.getDamager();
            e.getDamager().getServer().getConsoleSender().sendMessage(damager.getOpenInventory().getType().toString());
            if(damager.getOpenInventory().getType()!= InventoryType.CRAFTING)
                return true;
        }
        return false;
    }
    private boolean raycasttest(){
        if(this.e.getDamager() instanceof Player){
            Player damager = (Player) this.e.getDamager();
            Entity entity = this.e.getEntity();
            RayTraceResult raycast = new RayTraceResult(damager.getEyeLocation().toVector());
            double Xchange = Math.abs(raycast.getHitPosition().getX()-entity.getLocation().getX())-damager.getLocation().getDirection().getX();
            double Ychange = Math.abs(raycast.getHitPosition().getY()-entity.getLocation().getY());
            double Zchange = Math.abs(raycast.getHitPosition().getZ()-entity.getLocation().getZ())-damager.getLocation().getDirection().getZ();
            double XTolerance = entity.getWidth()*2;
            double YTolerance = entity.getHeight()*2*(Math.abs(damager.getLocation().getDirection().getY())+1);
            double Ztolerance = entity.getWidth()*2;
            if(XTolerance<1.4)
                XTolerance=1.4;
            if(Ztolerance<1.4)
                Ztolerance=1.4;
            e.getEntity().getServer().getConsoleSender().sendMessage(raycast.toString());
            e.getEntity().getServer().getConsoleSender().sendMessage(Xchange+" "+Ychange+" "+Zchange);
            e.getEntity().getServer().getConsoleSender().sendMessage(XTolerance+" "+YTolerance+" "+Ztolerance);
            e.getEntity().getServer().getConsoleSender().sendMessage( damager.getLocation().getDirection().toString());
            if(Math.abs(damager.getLocation().getDirection().getX())>Math.abs(damager.getLocation().getDirection().getZ())){
            if(Ychange>YTolerance||Zchange>Ztolerance){
                e.getEntity().getServer().getConsoleSender().sendMessage("Cheating detected");
                return true;
            }
            }
            else{
                if(Xchange>XTolerance||Ychange>YTolerance){
                    e.getEntity().getServer().getConsoleSender().sendMessage("Cheating detected");
                    return true;
                }
            }

        }

        return false;
    }

}
