package me.pythontest.unhacked.engines;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class RayCast {
    public Location startLocation;
    public Vector directon;
    public RayCast(Location startLocation, Vector directon){
    this.startLocation = startLocation;
    this.directon = directon;
    }
    public Vector getRayPoint(double distance){
        double X = startLocation.getX()+(directon.getX()*distance);
        double Y = startLocation.getY()+(directon.getY()*distance);
        double Z = startLocation.getZ()+(directon.getZ()*distance);
        return new Vector(X,Y,Z);
    }
}
