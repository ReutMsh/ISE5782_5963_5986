package primitives;

/**
 * Class Point define point
 * with double3
 * @author Reut and Odelya
 */
public class Point {

    public static final Point ZERO = new Point(0,0,0);
    /**
     * Move the point in DELTA size
     */
    private static final double DELTA = 0.1;

    Double3 xyz;

    //region constructor
    /**
     * constructor
     * @param x - value of x.
     * @param y - value of y.
     * @param z - value of z.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    /**
     * constructor
     * @param xyz - value of x,y,z.
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
    //endregion

    //region getter
    public double getX() {
        return xyz.d1;
    }
    public double getY() {
        return xyz.d2;
    }
    public double getZ() { return xyz.d3;}
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
     * @param p1 - point to subtract.
     * @return vector - this subtracts p1.
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     *add vector to the point
     * @param vector - vector to add.
     * @return point = point +  vector.
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * compute distance^2 between this and p1
     * @param p1 - point to compute distanceSquared from this.
     * @return distance^2 between this and p1
     */
    public double distanceSquared(Point p1){
        Double3 subPoint = xyz.subtract(p1.xyz);
        Double3 proPoint = new Double3(subPoint.d1 * subPoint.d1, subPoint.d2 * subPoint.d2, subPoint.d3 * subPoint.d3);
        return proPoint.d1 + proPoint.d2 + proPoint.d3;
    }

    /**
     * compute distance between this and p1
     * @param p1 - point to compute distance from this.
     * @return distance between this and p1
     */
    public double distance (Point p1){
        return Math.sqrt(this.distanceSquared(p1));
    }

    /**
     * @param direction - direction
     * @param t -  the distance to add the point.
     * @return a point that is at a distance of 't' from the point in the direction of a specific vector.
     */
    public Point getPoint(Vector direction , double t) {
        try {
            return this.add(direction.scale(t));
        }
        catch (Exception e){
            return this;
        }
    }

    /**
     * Move the point in DELTA size and in the direction of Vector.
     * @param direction - to check if to add DELTA or -DELTA.
     * @param n - direction of the vector.
     * @return point in DELTA size and in the direction of Vector.
     */
    public Point addDeltaPoint(Vector direction, Vector n) {
        return this.add(n.scale(n.dotProduct(direction) > 0 ? DELTA : -DELTA));
    }


    //endregion
}
