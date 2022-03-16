package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

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

    @Override
    public List<Point> findIntersections(Ray ray) {
        double th;
        double tm;

        //o==p
        if(center.equals(ray.getP0())){
            th = radius;
            tm = 0;
        }

        else {
            Vector u = center.subtract(ray.getP0());
            tm = ray.getDir().dotProduct(u);
            double dSquared = u.lengthSquared() - tm * tm;

            //the ray outside the sphere
            if (dSquared >= radius * radius) {
                return null;
            }

            th = Math.sqrt(radius * radius - dSquared);
        }
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if (t1 <= 0 && t2 <= 0){return null;}
        List<Point> list=new ArrayList<Point>();

        if (t1 > 0)
            list.add(ray.getPoint(t1));

        if (t2 > 0)
            list.add(ray.getPoint(t2));
        return list;
    }

    //endregion
}
