package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;
import geometries.Intersectable.GeoPoint;

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
       List<GeoPoint> gepPointList = this.scene.geometries.findGeoIntersections(ray);
       if(gepPointList == null)
       {
           return scene.background;
       }
       GeoPoint closestGeoPoint = ray.findClosestGeoPoint(gepPointList);
       return calcColor(closestGeoPoint);
    }

    /**
     * method of finding the color of a dot
     * @param GeoPoint
     * @return Color
     */
    private Color calcColor(GeoPoint GeoPoint)
    {
        return scene.ambientLight.getIntensity()
                .add(GeoPoint.geometry.getEmission());

    }
}
