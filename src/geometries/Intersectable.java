package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * findIntsersections with geometry
 */
public interface Intersectable {

    /**
     * find list of point that the geometry
     * cut with the ray
     * @param ray
     * @return List<Point>
     */
    public List<Point> findIntersections(Ray ray);
}
