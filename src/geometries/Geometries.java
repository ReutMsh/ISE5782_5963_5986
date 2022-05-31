package geometries;

import primitives.Ray;
import java.util.ArrayList;
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
     * constructor
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * constructor
     * @param geometries
     */
    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
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
     * @param geometries
     */
    public void add(Intersectable... geometries){
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }
    //endregion
}
