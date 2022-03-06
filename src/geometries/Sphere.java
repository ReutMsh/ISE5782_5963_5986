package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * class Sphere
 * define sphere with point and normal vector
 * implements Geometry
 * @author Reut and odelya
 */
public class Sphere implements Geometry{

    private Point center;
    private double radius;

    //region constructor
    /***
     * constructor
     * normalize the vector
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    //endregion

    //region getter

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    //endregion

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    //region methods
    @Override
    public Vector getNormal(Point p1) {
        return p1.subtract(center).normalize();
    }
    //endregion
}
