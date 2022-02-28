package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Cylinder define a final cylinder
 * extends Tube
 * add height
 * @author Reut and odelya
 */
public class Cylinder extends Tube{

    private double height;

    //region constructor
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }
    //endregion

    //region getter
    public double getHeight() {
        return height;
    }
    //endregion

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    //region methods
    @Override
    public Vector getNormal(Point p1) {
        return null;
    }
//endregion
}
