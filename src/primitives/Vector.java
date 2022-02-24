package primitives;
import static primitives.Double3.ZERO;

/**
 * class of vector
 * extends from Point
 */

public class Vector extends Point{

    //region constructors
    /***
     * constructor
     * @param x
     * @param y
     * @param z
     * throw exception if this zero vector
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if(xyz.equals(ZERO)){
            throw new IllegalArgumentException("ERROR: zero vector");}
    }

    /***
     * constructor
     * @param xyz
     * throw exception if this zero vector
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(ZERO)){
            throw new IllegalArgumentException("ERROR: zero vector");}
    }
//endregion


    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /***
     * add this with v1
     * @param v1
     * @return Vector
     */
    public Vector add(Vector v1){
        return new Vector(xyz.add(v1.xyz));
    }

    /***
     * scale this with v1
     * @param scalar
     * @return Vector
     */
    public Vector scale(double scalar){
        return  new Vector(xyz.scale(scalar));
    }

    /***
     * compute length^2
     * @return double
     */
    public double lengthSquared() {
        Double3 proPoint = xyz.product(xyz);
        return proPoint.d1 + proPoint.d2 + proPoint.d3;
    }

    /***
     * compute length
     * @return double
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /***
     * scalar multi
     * @param v3
     * @return double
     */
    public double dotProduct(Vector v3) {
        Double3 prod3 =xyz.product(v3.xyz);
        return prod3.d1 + prod3.d2 +prod3.d3;
    }

    /***
     * vector multi
     * @param v2
     * @return Vector
     */
    public Vector crossProduct(Vector v2) {
        double d1 = xyz.d2*v2.xyz.d3 - xyz.d3*v2.xyz.d2;
        double d2 = xyz.d3*v2.xyz.d1 - xyz.d1*v2.xyz.d3;
        double d3 = xyz.d1*v2.xyz.d2 - xyz.d2*v2.xyz.d1;
        return new Vector(d1,d2,d3);
    }

    /***
     * normalize the vector
     * @return Vector
     */
    public Vector normalize() {
        /**לבדוק אם 1/ עובד נכון*/
        double oneDivLength = 1/this.length();
        return new Vector(xyz.scale(oneDivLength));
    }
}
