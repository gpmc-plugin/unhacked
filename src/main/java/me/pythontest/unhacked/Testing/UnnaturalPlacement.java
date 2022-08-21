package me.pythontest.unhacked.Testing;

import me.pythontest.unhacked.TestMechanizm.UHPlayer;
import me.pythontest.unhacked.engines.RayCast;
import me.pythontest.unhacked.unhacked.Unhacked;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.BoundingBox;

import java.util.Date;

public class UnnaturalPlacement {
    private BlockPlaceEvent e;
    private Block block;
    public static double rayCastTolerance=0.5;
    public static double rayTracePrecision=0.05;
    public static double minLastBPSAmount = 3;
    public static double maxBPS=18;
    private Unhacked plugin;
    public UnnaturalPlacement(BlockPlaceEvent e,Unhacked plugin){
        this.e = e;
        this.block = e.getBlock();
        this.plugin=plugin;

    }
    public boolean test(){
       UHPlayer player = plugin.getStorageManager().getPlayer(e.getPlayer().getUniqueId().toString());
       double BPStestResult=BPStest();
       if(BPStestResult>0){
           player.addTest("UnnaturalPlacement", Component.text("Failed Block per secound test(FastPlace) \naverage last bps=" + BPStestResult));
           return true;
       }

        if(contactTest()){
            player.addTest("UnnaturalPlacement",Component.text("Placed blocks inposible to place"));
            return true;
        }
        double rayCastTestResult = rayCastTest();
        if(rayCastTestResult>0) {
            player.addTest("RayCastUnnaturalPlacement",Component.text( "RayCast test failed\nmissed "+rayCastTestResult+" blocks"));
            return true;
        }
        return false;
    }
    private boolean contactTest(){
        Location testingLocation;
        for(int i=0;i<6;i++){
            testingLocation= new Location(block.getWorld(),block.getX(),block.getY(),block.getZ());
            switch (i){
                case 0:
                    testingLocation.setX(testingLocation.getX()-1);
                    break;
                case 1:
                    testingLocation.setX(testingLocation.getX()+1);
                    break;
                case 2:
                    testingLocation.setY(testingLocation.getY()-1);
                    break;
                case 3:
                    testingLocation.setY(testingLocation.getY()+1);
                    break;
                case 4:
                    testingLocation.setZ(testingLocation.getZ()-1);
                    break;
                case 5:
                    testingLocation.setZ(testingLocation.getZ()+1);
                    break;

            }
            Block testingBlock = block.getWorld().getBlockAt(testingLocation);
            if(!testingBlock.isLiquid()&&!testingBlock.getType().isAir()){
                return false;
            }
        }
        return true;
    }
    public double rayCastTest(){
        double minDistance = -1;
        RayCast rayCast = new RayCast(e.getPlayer().getEyeLocation(), e.getPlayer().getEyeLocation().getDirection());
        BoundingBox blockContainer = new BoundingBox(block.getX()-rayCastTolerance, block.getY()-rayCastTolerance, block.getZ()-rayCastTolerance, block.getX()+1+rayCastTolerance, block.getY()+1+rayCastTolerance,block.getZ()+1+rayCastTolerance);
        for(double i=0;i<5.5;i+=rayTracePrecision){
            if(blockContainer.contains(rayCast.getRayPoint(i)))
                return -1;
            else{
                double distance=  RayCast.getDistanceToBoundingBox(rayCast.getRayPoint(i),blockContainer);
                if(minDistance<0||distance<minDistance)
                    minDistance=distance;
            }
        }
        return minDistance;
    }
    public double BPStest(){
        UHPlayer player = plugin.getStorageManager().getPlayer(e.getPlayer().getUniqueId().toString());
        if(player.lastBlockPlace==null)
            player.lastBlockPlace=new Date().getTime();
        else{
            Long now = new Date().getTime();
            Long difference = now- player.lastBlockPlace;
            if(difference<=0)
                difference= Long.valueOf(1);
            double bps = (1000/difference);
            player.lastBlockPlace=now;
            player.lastBPS.add(bps);
            if(player.lastBPS.size()>minLastBPSAmount){
                player.lastBPS.remove(0);
                double sum=0;
                for (Double lastBP : player.lastBPS) {
                    sum+=lastBP;
                }
                double average =sum/minLastBPSAmount;
                if(average>maxBPS) {
                    return average;
                }
            }
        }
        return -1;
    }
}
