package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;

/**
 * class Plane
 * define plane with point and normal vector
 * extends Geometry
 * @author Reut and Odelya
 */
public class Plane extends Geometry {

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

        try {
            Vector v1 = p1.subtract(p2);
            Vector v2 = p2.subtract(p3);

            normal = v1.crossProduct(v2).normalize();
            q0 = p1;
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("the 3 point can't build a plan");
        }
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

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        //if the ray start in p0 - there isn't intersection
        if(q0.equals(ray.getP0()))
            return null;

        Vector qMinusP0 = q0.subtract(ray.getP0());
        double nQMinusP0 = normal.dotProduct(qMinusP0);
        double nv = normal.dotProduct(ray.getDir());

        //if v orthogonal to the normal- v parallel to the plan
        if (isZero(nv))
        {return null;}

        double t = alignZero(nQMinusP0/nv);

        //there is intersection point

        if(t > 0d){
            if(alignZero(t - maxDistance) <= 0){
                List<GeoPoint> list = new ArrayList<GeoPoint>();
                list.add(new GeoPoint(this , ray.getPoint(t)));
                return list;
            }
        }

        //if t <= 0 - the ray start after the plane
        return null;
    }

    //endregion
}




