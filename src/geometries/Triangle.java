package geometries;

import primitives.Point;
import primitives.Vector;

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
}
