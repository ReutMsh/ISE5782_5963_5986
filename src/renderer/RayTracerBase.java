package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 *  Abstract Class RayTracerBase
 *
 * @author Reut and Odelya
 */
public abstract class RayTracerBase  {

    protected Scene scene;

    //region constructor
    /**
     * constructor
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    //endregion


    /**
     * abstract method
     * Find the color of the intersection point
     * @param ray
     * @return
     */
    public abstract Color traceRay(Ray ray);
}
