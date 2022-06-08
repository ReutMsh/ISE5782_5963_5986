package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * abstract class Intersectable
 * define all type of geometry
 * @author Reut and Odelya
 */
public abstract class Intersectable {

    //region class GeoPoint

    /**
     * Static Internal class
     * PDS
     */
    public static class GeoPoint {
        public final Geometry geometry;
        public final Point point;

        /**
         * constructor
         * @param geometry - the geometry of the point
         * @param point - the specific point on the geometry.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

    }
    //endregion

    //region methods
    /**
     * Abstract method - find list of point that the ray intersection the geometry.
     * @param ray - the ray to find the intersection with the geometry.
     * @return list of point that the ray intersection the geometry
     */
    public final List<Point> findIntersections(Ray ray)
    {
        List<GeoPoint> geoList = findGeoIntersections(ray , Double.POSITIVE_INFINITY);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point). toList();
    }

    /**
     * call to findGeoIntersectionsHelper - abstract method.
     * NVI
     * @param ray - the ray to find the intersection with the geometry.
     * @param maxDistance - the max distance between point and p0(start ray)
     * @return list of GeoPoint that the ray intersection the geometry, and the distance between p0(start ray)  and point < maxDistance.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance)
    {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Abstract internal method for finding a point of intersection with geometries
     * and the distance between p0(start ray) and point < maxDistance
     * @param ray - the ray to find the intersection with the geometry.
     * @param maxDistance - the max distance between point and p0(start ray)
     * @return list of GeoPoint that the ray intersection the geometry, and the distance between p0(start ray)  and point < maxDistance.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);
    //endregion
}
