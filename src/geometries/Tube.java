package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class Tube
 * define Tube with Ray and double radius
 * implements Geometry
 * @author Reut and odelya
 */
public class Tube implements Geometry{

    protected Ray axisRay;
    protected double radius;

    //region constructor
    /***
     * constructor
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }
    //endregion

    //region getter
    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }
    //endregion

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    //region methods

    @Override
    public Vector getNormal(Point p1) {
        //t= A levy on the axis of the tube
        double t = axisRay.getDir().dotProduct(p1.subtract(axisRay.getP0()));
        //The center of the Galilee in relation to p1
        Point O = axisRay.getP0().add(axisRay.getDir().scale(t));
        return  p1.subtract(O).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    //endregion
}
