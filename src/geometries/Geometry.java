package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * interface Geometry
 * define all the geometry behavior
 * @author Reut and odelya
 */
public abstract class Geometry extends Intersectable  {

    protected Color emission = Color.BLACK;

    //region get and set
    public Color getEmission()
    {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    //endregion

    //region methods
    /***
     * get normal in this point
     * @param p1
     * @return Vector
     */
    public abstract Vector getNormal(Point p1);


    //endregion
}
