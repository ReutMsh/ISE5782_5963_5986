package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{
    protected Ray axisRay;
    protected double radius;

    /***
     * constructor
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

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

    @Override
    public Vector getNormal(Point p1) {
        return null;
    }
}
