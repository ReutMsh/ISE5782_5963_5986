package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import java.util.List;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

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

    //region private method
    private Color calcLocalEffects(GeoPoint geoPointIntersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPointIntersection.geometry.getNormal(geoPointIntersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = geoPointIntersection.geometry.getMaterial().nShininess;
        Double3 kd = geoPointIntersection.geometry.getMaterial().kD;
        Double3 ks = geoPointIntersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPointIntersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(geoPointIntersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity, nl),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity, nl));
            }
        }
        return color;
    }


    /**
     *
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return
     */
    private Color calcDiffusive(Double3 kd, Vector l,Vector n, Color lightIntensity, double nl){
        if(nl < 0 )
            nl = -nl;
        Double3 kdLn = kd.scale(nl);
        return  lightIntensity.scale(kdLn);
    }

    /**
     *
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return
     */
    private Color calcSpecular(Double3 ks, Vector l,Vector n, Vector v, int nShininess, Color lightIntensity, double ln){
        Vector r  = l.add(n.scale(ln*-2)).normalize();
        double max = Math.max(0, (v.scale(-1)).dotProduct(r));
        double maxNs = Math.pow(max, nShininess);

        Double3 ksMaxNs = ks.scale(maxNs);

        return  lightIntensity.scale(ksMaxNs);
    }


    //endregion

    //region method
    @Override
    public Color traceRay(Ray ray) {
       List<GeoPoint> gepPointList = this.scene.geometries.findGeoIntersections(ray);
       if(gepPointList == null)
       {
           return scene.background;
       }
       GeoPoint closestGeoPoint = ray.findClosestGeoPoint(gepPointList);
       return calcColor(closestGeoPoint, ray);
    }

    /**
     * method of finding the color of a dot
     * @param geoPoint
     * @return Color
     */
    private Color calcColor(GeoPoint geoPoint , Ray ray)
    {
        return scene.ambientLight.getIntensity()
                .add(geoPoint.geometry.getEmission()).add(calcLocalEffects(geoPoint, ray));

    }
    //endregion
}
