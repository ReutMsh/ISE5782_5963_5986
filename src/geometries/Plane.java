package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * class Plane
 * define plane with point and normal vector
 * implements Geometry
 * @author Reut and odelya
 */
public class Plane implements Geometry {

    private Point q0;
    private Vector normal;

    //region constructors
    /**
     * constructor
     * normalize the normal
     * @param q0
     * @param normal
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * constructor
     * found the normal from 3 points
     * check if the 3 point can build plan
     * throw exception from vector if the 3 point can't build a plan
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p1.subtract(p2);
        Vector v2 = p2.subtract(p3);

        /**throw exception from point if the 3 point can't build a plan*/
        normal = v1.crossProduct(v2);
        q0 = p1;
    }
//endregion

    //region getter
    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }
    //endregion

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    //region methods
    @Override
    public Vector getNormal(Point p1) {
        return normal;
    }
    //endregion

}
