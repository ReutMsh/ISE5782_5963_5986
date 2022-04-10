package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;
import java.util.Objects;

/**
 * abstract class Intersectable
 * define all type of geometry
 * @author Reut and odelya
 */
public abstract class Intersectable {

    //region GeoPoint

    /**
     * Static Internal class
     * PDS
     */
    public static class GeoPoint {
        public final Geometry geometry;
        public final Point point;

        /**
         * constructor
         *
         * @param geometry
         * @param point
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

    /**
     * Abstract method - find list of point that the geometry
     * cut with the ray
     *
     * @param ray
     * @return List<Point>
     */
    public final List<Point> findIntersections(Ray ray)
    {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point). toList();
    }

    /**
     * find list of GeoPoint that the geometry
     * cut with the ray
     * NVI
     * @param ray
     * @return
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Abstract internal method for finding a point of intersection with geometries
     *
     * @param ray
     * @return
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
