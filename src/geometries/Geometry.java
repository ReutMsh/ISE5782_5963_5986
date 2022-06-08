package geometries;

import primitives.*;


/**
 * abstract class Geometry
 * define all the geometry behavior
 * @author Reut and Odelya
 */
public abstract class Geometry extends Intersectable  {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    //region get

    public Color getEmission()
    {
        return emission;
    }

    public Material getMaterial() {
        return material;
    }

    //endregion

    //region set

    /**
     * builder setEmission
     * @param emission - the emission of the geometry.
     * @return this (builder)
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * builder setMaterial
     * @param material - the material of the geometry.
     * @return this (builder)
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
    //endregion

    //region methods
    /***
     * abstract method
     * get normal of the geometry in specific point
     * @param p1 - specific point to get the normal.
     * @return vector normal.
     */
    public abstract Vector getNormal(Point p1);


    //endregion
}
