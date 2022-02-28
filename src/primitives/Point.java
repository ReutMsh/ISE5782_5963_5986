package primitives;
import java.util.Objects;
import static primitives.Double3.ZERO;

/**
 * Class Point define point
 * with double3
 * @author Reut and odelya
 */
public class Point {

    Double3 xyz;

    //region constructor
    /**
     * constructor
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    /**
     * constructor
     * @param xyz
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
//endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    //region methods
    /**
     * build vector with 2 points
     * @param p1
     * @return Vector
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     *add vector to the point
     * @param vector
     * @return Point
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * compute distance^2 between this and p1
     * @param p1
     * @return double
     */
    public double distanceSquared(Point p1){
        Double3 subPoint = xyz.subtract(p1.xyz);
        Double3 proPoint = new Double3(subPoint.d1 * subPoint.d1, subPoint.d2 * subPoint.d2, subPoint.d3 * subPoint.d3);
        return proPoint.d1 + proPoint.d2 + proPoint.d3;
    }

    /**
     * compute distance between this and p1
     * @param p1
     * @return double
     */
    public double distance (Point p1){
        return Math.sqrt(this.distanceSquared(p1));
    }
    //endregion
}
