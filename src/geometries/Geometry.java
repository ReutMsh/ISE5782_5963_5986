package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface Geometry
 * define all the geometry behavior
 * @author Reut and odelya
 */
public interface Geometry extends Intersectable  {

    /***
     * get normal in this point
     * @param p1
     * @return Vector
     */
    public Vector getNormal(Point p1);
}
