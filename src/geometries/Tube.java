package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }
    //endregion
}
