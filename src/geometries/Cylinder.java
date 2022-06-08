package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class Cylinder define a final cylinder
 * extends Tube
 * add height
 * @author Reut and Odelya
 */
public class Cylinder extends Tube{

    private double height;

    //region constructor

    /**
     * constructor
     * @param axisRay - the axis of the cylinder.
     * @param radius - the radius of the cylinder.
     * @param height - the height of the cylinder
     */
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

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point o = ray.getP0();
        Vector d = ray.getDir();
        Vector local_z = axisRay.getDir().normalize();
        Vector local_x;
        try {
            local_x = d.crossProduct(local_z).normalize();
        }
        catch (Exception e){
            return null;
        }
        Vector local_y = local_z.crossProduct(local_x).normalize();
        Vector w = o.subtract(axisRay.getP0());
        Vector o_local = new Vector(w.dotProduct(local_x), w.dotProduct(local_y), w.dotProduct(local_z));
        Vector d_local = new Vector(d.dotProduct(local_x), d.dotProduct(local_y), d.dotProduct(local_z));
        double a = d_local.getY() * d_local.getY();
        double b = 2 * d_local.getY() * o_local.getY();
        double c = o_local.getY() * o_local.getY() + o_local.getX() * o_local.getX() - radius * radius;
        if (a == 0) {
            return null;
        }
        double e = b * b - 4 * a * c;
        if (e < 0) {
            return null;
        }

        e = Math.sqrt(e);

        List<GeoPoint> pointList = new ArrayList<>();

        double t1 = (-b - e) / (2 * a);
        double t2 = (-b + e) / (2 * a);
        Point p2 = axisRay.getP0().getPoint(axisRay.getDir(), height);
        if (alignZero(t1) > 0d) {
            Point i = ray.getPoint(t1);
            double f = i.subtract(axisRay.getP0()).dotProduct(local_z);
            if (f > 0 && f < p2.subtract(axisRay.getP0()).length()) {
                pointList.add(new GeoPoint(this , i));
            }
        }
        if (alignZero(t2) > 0d) {
            Point i = ray.getPoint(t2);
            double f = i.subtract(axisRay.getP0()).dotProduct(local_z);
            if (f > 0 && f < p2.subtract(axisRay.getP0()).length()) {
                pointList.add(new GeoPoint(this , i));
            }
        }
        if(pointList.size() == 0)
            return null;
        return pointList;
    }

    //endregion
}
