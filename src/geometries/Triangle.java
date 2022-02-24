package geometries;

import primitives.Point;
import primitives.Vector;

public class Triangle extends Polygon{

    /***
     * constructor
     * get 3 points
     * @param vertices
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

}
