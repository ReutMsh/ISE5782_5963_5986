package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{
    private Point center;
    private Vector normal;

    /***
     * constructor
     * normalize the vector
     * @param center
     * @param normal
     */
    public Sphere(Point center, Vector normal) {
        this.center = center;
        this.normal = normal.normalize();
    }

    //region getter
    public Point getCenter() {
        return center;
    }

    public Vector getNormal() {
        return normal;
    }
    //endregion

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", normal=" + normal +
                '}';
    }

    @Override
    public Vector getNormal(Point p1) {
        return null;
    }


}
