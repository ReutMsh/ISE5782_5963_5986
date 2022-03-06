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
        //check if the point on the bottom base of the cylinder
        if (p1.distanceSquared(axisRay.getP0()) < radius*radius)
            return axisRay.getDir();
        //check if the point on the top base of the cylinder
        if(p1.distanceSquared(axisRay.getP0().add(axisRay.getDir().scale(height))) < radius*radius)
            return axisRay.getDir().scale(-1);

        return super.getNormal(p1);
    }
//endregion
}
