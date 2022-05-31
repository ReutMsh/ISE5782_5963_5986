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
     * setEmission
     * @param emission
     * @return Geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * setMaterial
     * @param material
     * @return Geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
    //endregion

    //region methods
    /***
     * abstract method
     * get normal in this point
     * @param p1
     * @return Vector
     */
    public abstract Vector getNormal(Point p1);


    //endregion
}
