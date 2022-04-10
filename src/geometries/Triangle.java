package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

/**
 * class Triangle
 * extends Polygon
 * @author Reut and odelya
 */
public class Triangle extends Polygon{

    //region constructor
    /***
     * constructor
     * get 3 points
     * @param vertices
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }
    //endregion

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //check if the ray intersection the plan
        List<Point> pointListFromPlane = plane.findIntersections(ray);

        if(pointListFromPlane == null) { return null; }

        Vector v1= vertices.get(0).subtract(ray.getP0());
        Vector v2= vertices.get(1).subtract(ray.getP0());
        Vector v3= vertices.get(2).subtract(ray.getP0());

        Vector n1 =  v1.crossProduct(v2).normalize();
        Vector n2 =  v2.crossProduct(v3).normalize();
        Vector n3 =  v3.crossProduct(v1).normalize();

        double vn1 = alignZero(ray.getDir().dotProduct(n1));
        double vn2 = alignZero(ray.getDir().dotProduct(n2));
        double vn3 = alignZero(ray.getDir().dotProduct(n3));

        if((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0))
        {
            List<GeoPoint> geoPointsTriangle = new ArrayList<GeoPoint>();
            for (Point point: pointListFromPlane)
            {
                geoPointsTriangle.add(new GeoPoint(this , point));
            }
            return geoPointsTriangle;
        }
        return null;
    }
}
