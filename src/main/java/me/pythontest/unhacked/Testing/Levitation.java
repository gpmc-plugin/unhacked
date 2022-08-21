package me.pythontest.unhacked.Testing;

import me.pythontest.unhacked.TestMechanizm.UHPlayer;
import me.pythontest.unhacked.unhacked.Unhacked;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class Levitation {
    private PlayerMoveEvent e;
    private Player player;
    private Unhacked plugin;
    public Levitation(PlayerMoveEvent e, Unhacked plugin){
        this.e=e;
        this.player = e.getPlayer();
        this.plugin = plugin;
    }
    public boolean test(){
        UHPlayer player = plugin.getStorageManager().getPlayer(e.getPlayer().getUniqueId().toString());
        if(isValidConditions()){
            double yChange = YchangeTest();
            if(yChange<=0)
                player.addTest("AntyLevitation", Component.text("y change=" + yChange));

        }
     return false;
    }
    public boolean isValidConditions(){
        Location playerLocation = new Location(this.player.getWorld(),this.player.getLocation().getX(),this.player.getLocation().getY(),this.player.getLocation().getZ());
        if(this.player.getVehicle()==null&&e.hasChangedPosition()&&this.player.getGameMode().equals(GameMode.SURVIVAL)&&checkBlockArround()){
            if(this.player.getPotionEffect(PotionEffectType.LEVITATION)!=null){
                if(this.player.getWorld().getBlockAt(this.player.getLocation().getBlock().getLocation()).getType().isAir()){
                    if(!this.player.isInWater()||!this.player.isInLava()) {
                        playerLocation.setY(playerLocation.getY() + 2);
                        if (this.player.getWorld().getBlockAt(playerLocation).getType().isAir())
                            return true;
                    }
                }


            }
        }
        return false;
    }
    public boolean checkBlockArround(){
        Location playerLocation = new Location(this.player.getWorld(),this.player.getLocation().getX(),this.player.getLocation().getY(),this.player.getLocation().getZ());
        if(playerLocation.getX()>=0)
            playerLocation.setX(Math.ceil(playerLocation.getX()));
        else
            playerLocation.setX(Math.floor(playerLocation.getX()-1));
        if(this.player.getWorld().getBlockAt(playerLocation).getType().isAir()){
            playerLocation = new Location(this.player.getWorld(),this.player.getLocation().getX(),this.player.getLocation().getY(),this.player.getLocation().getZ());
            if(playerLocation.getZ()>=0)
                playerLocation.setZ(Math.ceil(playerLocation.getZ()));
            else
                playerLocation.setZ(Math.floor(playerLocation.getZ()-1));
            if(this.player.getWorld().getBlockAt(playerLocation).getType().isAir())
                return true;
        }
        return false;
    }
    public double YchangeTest(){
        if(e.getFrom().getY()>e.getTo().getY()){
            return e.getTo().getY()-e.getFrom().getY();
        }
        return 1;
    }
}
