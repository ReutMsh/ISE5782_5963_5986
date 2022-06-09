package primitives;

import java.util.List;
import geometries.Intersectable.GeoPoint;


/**
 * Class Ray define ray
 * with normalise vector and point
 * @author Reut and Odelya
 */
public class Ray {

    /**
     *fixed for moving the beginning of the rays. Of shading rays
     */
    private static final double DELTA = 0.1;

    private final Point p0;
    private final Vector dir;

    //region constructor

    /**
     * constructor
     * normalize the vector and build the ray
     * @param p0 - start of ray.
     * @param dir - direction of ray.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * constructor - build rat that start DELTA after head.
     * @param head - start of ray.
     * @param direction - direction of ray.
     * @param n - add head in direction n.
     */
    public Ray(Point head, Vector direction, Vector n) {
        p0 = head.add(n.scale(n.dotProduct(direction) > 0 ? DELTA : -DELTA));
        dir = direction;
    }
    //endregion

    //region get

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }


    /**
     * @param t - the distance to add the point.
     * @return point that is at a distance of 't' from the beginning of the ray.
     */
    public Point getPoint(double t) {
        try {
            return p0.add(dir.scale(t));
        }
        catch (Exception e){
            return p0;
        }
    }


    /**
     * Receives a list of points and returns the point
     * closest to the beginning of the fund
     * @param pointList - list of point.
     * @return closes point from pointList.
     */
    public Point findClosestPoint(List<Point> pointList)
    {
        return pointList == null || pointList.isEmpty() ? null
                : findClosestGeoPoint(pointList.stream().map(p -> new GeoPoint(null, p)).toList()).point;

    }

    /**
     * Receives a list of GeoPoints and returns the GeoPoint
     * closest to the beginning of the fund
     * @param GeoPointList - list of geoPoint.
     * @return closes geoPoint from pointList.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> GeoPointList)
    {
        if(GeoPointList == null)
            return null;
        GeoPoint closesPoint = GeoPointList.get(0);
        double minDistanceSquared = closesPoint.point.distanceSquared(this.p0);
        double distanceSquared;
        for (GeoPoint geoPoint: GeoPointList)
        {
            distanceSquared = geoPoint.point.distanceSquared(this.p0);
            if(distanceSquared < minDistanceSquared)
            {
                minDistanceSquared = distanceSquared;
                closesPoint = geoPoint;
            }
        }
        return closesPoint;
    }

}
