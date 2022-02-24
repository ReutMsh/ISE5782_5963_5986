package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{

    private Point center;
    private Vector normal;

    //region constructor
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
    //endregion

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

    //region methods
    @Override
    public Vector getNormal(Point p1) {
        return null;
    }
    //endregion
}
