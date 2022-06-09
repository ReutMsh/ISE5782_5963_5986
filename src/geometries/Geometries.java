package geometries;

import primitives.Ray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class Geometries
 * Department for the Association of Geometric Bodies
 * implements Intersectable
 * @author Reut and Odelya
 */
public class Geometries extends Intersectable {

    List<Intersectable> geometries;

    //region constructor

    /**
     * default constructor
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * constructor that get geometries to list
     * @param geometries - some geometries to add to geometries list.
     */
    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();
        Collections.addAll(this.geometries, geometries);
    }

    //endregion

    //region method
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> listOfAllThePoint = new ArrayList<>();

        for (Intersectable geometry : geometries) {
            List<GeoPoint> pointList = geometry.findGeoIntersectionsHelper(ray, maxDistance);
            if (pointList == null) continue;
            listOfAllThePoint.addAll(pointList);
        }

        if (listOfAllThePoint.size() == 0)
            return null;
        return listOfAllThePoint;
    }

    /**
     * Add geometry to the list
     * @param geometries - some geometries to add to the geometries list.
     */
    public void add(Intersectable... geometries){
        Collections.addAll(this.geometries, geometries);
    }
    //endregion
}
