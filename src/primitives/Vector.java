package primitives;

import static primitives.Util.alignZero;

/**
 * Class Vector define Vector
 * extends from point
 * @author Reut and Odelya
 */
public class Vector extends Point{

    //region constructors

    /**
     * constructor
     * @param x
     * @param y
     * @param z
     * throw exception if this zero vector
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if(xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("ERROR: zero vector");}
    }

    /**
     * constructor
     * @param xyz
     * throw exception if this zero vector
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(Double3.ZERO)){
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

    //region methods

    /**
     * add this with v1
     * @param v1
     * @return Vector
     */
    public Vector add(Vector v1){
        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * scale Vector with scalar
     * @param scalar
     * @return Vector
     */
    public Vector scale(double scalar){
        return  new Vector(xyz.scale(scalar));
    }

    /**
     * compute length^2
     * @return double
     */
    public double lengthSquared() {
        Double3 proPoint = xyz.product(xyz);
        return proPoint.d1 + proPoint.d2 + proPoint.d3;
    }

    /**
     * compute length
     * @return double
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * scalar multi
     * @param v3
     * @return double
     */
    public double dotProduct(Vector v3) {
        Double3 prod3 =xyz.product(v3.xyz);
        return prod3.d1 + prod3.d2 +prod3.d3;
    }

    /**
     * vector multi
     * @param v2
     * @return Vector
     */
    public Vector crossProduct(Vector v2) {
        double d1 =  alignZero(xyz.d2*v2.xyz.d3 - xyz.d3*v2.xyz.d2);
        double d2 = alignZero(xyz.d3*v2.xyz.d1 - xyz.d1*v2.xyz.d3);
        double d3 = alignZero(xyz.d1*v2.xyz.d2 - xyz.d2*v2.xyz.d1);
        return new Vector(d1,d2,d3);
    }

    /**
     * normalize the vector
     * @return Vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(this.length()));
    }

    /**
     * return min between d1,d2,d3
     * @return Vector
     */
    public Vector minD(){
        if (xyz.d1 < xyz.d2 && xyz.d1 < xyz.d3)
            return new Vector(0, xyz.d3, -xyz.d2);
        if (xyz.d2 < xyz.d1 && xyz.d2 < xyz.d3)
            return new Vector(xyz.d3, 0, -xyz.d1);

        return new Vector(xyz.d3, 0, -xyz.d1);

    }
    //endregion

}
