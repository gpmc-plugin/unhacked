package me.pythontest.unhacked.engines;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;
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
    public static double getDistanceToBoundingBox(Vector point, BoundingBox boundingBox){
        if(boundingBox.contains(point))
            return 0;
        Vector boundingBoxPoint=new Vector();
        if(boundingBox.getMinX()<=point.getX()&&point.getX()<=boundingBox.getMaxX())
            boundingBoxPoint.setX(point.getX());
        else if(boundingBox.getMinX()>=point.getX())
            boundingBoxPoint.setX(boundingBox.getMinX());
        else
            boundingBoxPoint.setX(boundingBox.getMaxX());
        if(boundingBox.getMinY()<=point.getY()&&point.getY()<=boundingBox.getMaxY())
            boundingBoxPoint.setY(point.getY());
        else if(boundingBox.getMinY()>=point.getY())
            boundingBoxPoint.setY(boundingBox.getMinY());
        else
            boundingBoxPoint.setY(boundingBox.getMaxY());
        if(boundingBox.getMinZ()<=point.getZ()&&point.getZ()<=boundingBox.getMaxZ())
            boundingBoxPoint.setZ(point.getZ());
        else if(boundingBox.getMinZ()>=point.getZ())
            boundingBoxPoint.setZ(boundingBox.getMinZ());
        else
            boundingBoxPoint.setZ(boundingBox.getMaxZ());
        return point.distance(boundingBoxPoint);
    }
}
