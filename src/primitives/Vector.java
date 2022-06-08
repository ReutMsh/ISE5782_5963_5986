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
     * @param x - value of x.
     * @param y - value of y.
     * @param z - value of z.
     * throw exception if this zero vector
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if(xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("ERROR: zero vector");}
    }

    /**
     * constructor
     * @param xyz - value of x,y,z.
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
     * @param v1 - vector to add.
     * @return this + v1.
     */
    public Vector add(Vector v1){
        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * scale Vector with scalar
     * @param scalar - scalar to scale the vector (this).
     * @return this * scalar.
     */
    public Vector scale(double scalar){
        return  new Vector(xyz.scale(scalar));
    }

    /**
     * compute length^2
     * @return lengthSquared of this.
     */
    public double lengthSquared() {
        Double3 proPoint = xyz.product(xyz);
        return proPoint.d1 + proPoint.d2 + proPoint.d3;
    }

    /**
     * compute length
     * @return length of this.
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * scalar multi
     * @param v - vector to dot product.
     * @return this * v.
     */
    public double dotProduct(Vector v) {
        Double3 prod3 =xyz.product(v.xyz);
        return prod3.d1 + prod3.d2 +prod3.d3;
    }

    /**
     * vector multi
     * @param v - vector to cross product.
     * @return this x v.
     */
    public Vector crossProduct(Vector v) {
        double d1 =  alignZero(xyz.d2*v.xyz.d3 - xyz.d3*v.xyz.d2);
        double d2 = alignZero(xyz.d3*v.xyz.d1 - xyz.d1*v.xyz.d3);
        double d3 = alignZero(xyz.d1*v.xyz.d2 - xyz.d2*v.xyz.d1);
        return new Vector(d1,d2,d3);
    }

    /**
     * normalize the vector
     * @return normalize this (length = 1).
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(this.length()));
    }

    /**
     * min d = 0, the other d -> replace and one of them scale -1.
     * @return vector vertical for this.
     */
    public Vector verticalVector(){
        if (xyz.d1 < xyz.d2 && xyz.d1 < xyz.d3)
            return new Vector(0, xyz.d3, -xyz.d2);
        if (xyz.d2 < xyz.d1 && xyz.d2 < xyz.d3)
            return new Vector(xyz.d3, 0, -xyz.d1);
        return new Vector(xyz.d2, -xyz.d1, 0);

    }
    //endregion

}
