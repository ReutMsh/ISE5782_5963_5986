package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
     * @param scene - save all the information of scene.
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    //endregion

    /**
     * abstract method
     * Find the color of the intersection point
     * @param ray - pixel ray - found the color of the pixel.
     * @return color of pixel (that ray intersection it).
     */
    public abstract Color traceRay(Ray ray);

    /**
     * abstract method
     * for Anti-Aliasing
     * get list of Ray, find the average color between all the color of intersection points
     * @param rayList - pixel rays - found the color of the pixel.
     * @return color of pixel (that rays intersection it).
     */
    public abstract Color traceRay(List<Ray> rayList);

    }
