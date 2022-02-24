package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {

    /***
     * get normal in this point
     * @param p1
     * @return Vector
     */
    public Vector getNormal(Point p1);
}
