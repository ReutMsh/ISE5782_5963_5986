package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * Class RayTracerBasic
 *
 * @author Reut and Odelya
 */
public class RayTracerBasic extends RayTracerBase {

    //region constructor
    /**
     * constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    //endregion

    @Override
    public Color traceRay(Ray ray) {
       List<Point> pointList = this.scene.geometries.findIntersections(ray);
       if(pointList == null)
       {
           return scene.background;
       }
       Point closestPoint = ray.findClosestPoint(pointList);
       return calcColor(closestPoint);
    }

    /**
     * method of finding the color of a dot
     * @param point
     * @return Color
     */
    private Color calcColor(Point point)
    {
        return scene.ambientLight.getIntensity();
    }
}
