package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * class Geometries
 * Department for the Association of Geometric Bodies
 * implements Intersectable
 * @author Reut and odelya
 */
public class Geometries implements Intersectable {

    List<Intersectable> geometries;

    //region constructor
    public Geometries() {
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    //endregion

    //region method

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> listOfAllThePoint = new ArrayList<>();

        for (Intersectable geometry : geometries) {
            List<Point> pointList = geometry.findIntersections(ray);
            if (pointList == null) continue;
            listOfAllThePoint.addAll(pointList);
        }

        if (listOfAllThePoint.size() == 0)
            return null;
        return listOfAllThePoint;
    }

    /**
     * add geometry to the list
     * @param geometries
     */
    public void add(Intersectable... geometries){
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }
//endregion
}