package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import java.util.ArrayList;
import java.util.List;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

/**
 * Class RayTracerBasic
 * responsible for finding the color of the pixel
 * @author Reut and Odelya
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * max level to find Transparencies / reflections
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * minimum Percentage to calculate transparencies / reflections
     */
    private static final Double3 MIN_CALC_COLOR_K = new Double3(0.001);
    /**
     * Percentage of initial impact of transparencies / reflections
     */
    private static final Double3 INITIAL_K = new Double3(1.0);

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

    //region calcColor

    /**
     * find the color of the pixel.
     * add Only once the ambientLight and call to calcColor that call
     * to calcGlobalEffects and calcLocalEffects.
     * @param geoPoint
     * @param ray
     * @return Color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * method of finding the color of a pixel
     * call to calcGlobalEffects
     * @param geoPoint
     * @return Color
     */
    private Color calcColor(GeoPoint geoPoint , Ray ray, int level, Double3 k)
    {
        Color color = calcLocalEffects(geoPoint, ray, k).add(geoPoint.geometry.getEmission());

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));

    }

    //endregion

    //region Local

    /**
     * find the local color of the pixel
     * @param geoPointIntersection
     * @param ray
     * @param k
     * @return Color
     */
    private Color calcLocalEffects(GeoPoint geoPointIntersection, Ray ray, Double3 k) {
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
                Double3 ktr = transparency(geoPointIntersection, lightSource, l, n);
                if (MIN_CALC_COLOR_K.subtract(ktr.product(k)).lowerThan(0) ){
                    Color lightIntensity = lightSource.getIntensity(geoPointIntersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity, nl),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity, nl));
                }
            }
        }
        return color;
    }

    /**
     * refactor - > transparency
     * check if the pixel unshaded
     * @param geoPoint
     * @param l
     * @param n
     * @param lightSource
     * @return boolean
     */
    private boolean unshaded(GeoPoint geoPoint, Vector l, Vector n, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = lightSource.getDistance(geoPoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null)
            return true;

        Double3 transparent = Double3.ONE;
        for (var geo : intersections) {
            transparent = transparent.product(geo.geometry.getMaterial().kT);
            if (transparent.subtract(MIN_CALC_COLOR_K).lowerThan(0))
                return false;
        }

        return true;
    }

    //region transparency

    /**
     *check if the pixel unshaded
     * and how much it is shaded
     * *check with only one ray
     * @param geoPoint
     * @param lightSource
     * @param l
     * @param n
     * @return double
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = lightSource.getDistance(geoPoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null)
            return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (var geo : intersections) {
            ktr = ktr.product(geo.geometry.getMaterial().kT);
            if (ktr.subtract(MIN_CALC_COLOR_K).lowerThan(0))
                return Double3.ZERO;
        }
        return ktr;
    }

    /**
     * for softShadow
     *check if the pixel unshaded
     * and how much it is shaded
     * *check with multi rays.
     * @param geoPoint
     * @param lightSource
     * @param l
     * @param n
     * @param countRay
     * @return
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n, int countRay){
        Vector lightDirection = l.scale(-1); // from point to light source

        List<Ray> rayList = new ArrayList<>();
        rayList.add(new Ray(geoPoint.point, lightDirection, n)); //add the center ray.
        MultipleRay multipleRay = new MultipleRay(lightSource.getBlackBoard(geoPoint.point), countRay-1);
        rayList.addAll(multipleRay.constructMultipleRay(geoPoint.point.addDeltaPoint(lightDirection, n)));
        Double3 ktr = Double3.ZERO;
        for (Ray ray: rayList) {
            double lightDistance = lightSource.getDistance(geoPoint.point);
            var intersections = scene.geometries.findGeoIntersections(ray, lightDistance);
            if (intersections == null)
                ktr = ktr.add(Double3.ONE);
            else {
                Double3 partAtr = Double3.ONE;
                for (var geo : intersections) {
                    partAtr = partAtr.product(geo.geometry.getMaterial().kT);
                    if (partAtr.subtract(MIN_CALC_COLOR_K).lowerThan(0))
                        ktr = ktr.add(Double3.ZERO);
                    else
                        ktr = ktr.add(partAtr);
                }
            }
        }
        return ktr.reduce(rayList.size());
    }

    //endregion

    /**
     *compute the diffuse effect of light on the pixel.
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return Color
     */
    private Color calcDiffusive(Double3 kd, Vector l,Vector n, Color lightIntensity, double nl){
        if(nl < 0 )
            nl = -nl;
        Double3 kdLn = kd.scale(nl);
        return  lightIntensity.scale(kdLn);
    }

    /**
     *compute the specular effect of light on the pixel.
     * (The reflected light)
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return Color
     */
    private Color calcSpecular(Double3 ks, Vector l,Vector n, Vector v, int nShininess, Color lightIntensity, double ln){
        Vector r  = l.add(n.scale(ln*-2)).normalize();
        double max = Math.max(0, (v.scale(-1)).dotProduct(r));
        double maxNs = Math.pow(max, nShininess);

        Double3 ksMaxNs = ks.scale(maxNs);

        return  lightIntensity.scale(ksMaxNs);
    }
    //endregion

    //region calcGlobal
    /**
     * Recursive func
     *compute the global color
     * @param geoPoint
     * @param ray
     * @return Color
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Double3 kr = geoPoint.geometry.getMaterial().kR , kkr = k.product(kr);

        if ( MIN_CALC_COLOR_K.subtract(kkr).lowerThan(0)) {
            Ray reflectedRay = constructReflectedRay(n,  geoPoint.point, ray.getDir());
            color = color.add(calcGlobalEffects(reflectedRay, level, kr, kkr));
        }

        Double3 kt = geoPoint.geometry.getMaterial().kT, kkt = k.product(kt);
        if ( MIN_CALC_COLOR_K.subtract(kkt).lowerThan(0)) {
            Ray refractedRay = new Ray(geoPoint.point, ray.getDir() , n);
            color = color.add(calcGlobalEffects(refractedRay, level, kt, kkt));
        }

        return color;
    }

    /**
     * the func that call to Recursive calcGlobalEffects
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffects(Ray ray, int level, Double3 kx, Double3 kkx){
        GeoPoint geoPoint = findClosestIntersection(ray);
        return (geoPoint == null? scene.background : calcColor(geoPoint, ray, level-1, kkx).scale(kx));
    }

    /**
     * build the reflected ray
     * @param n
     * @param point
     * @param v
     * @return Ray
     */
    private Ray constructReflectedRay(Vector n, Point point, Vector v) {
        double vn = v.dotProduct(n);
        Vector r  = v.add(n.scale(vn*-2)).normalize();
        return new Ray(point, r, n);
    }

    //endregion

    /**
     * find the close intersection GeoPoint
     * @param ray
     * @return GeoPoint
     */
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> intersectionGeoPoint = this.scene.geometries.findGeoIntersections(ray, Double.POSITIVE_INFINITY);
        return ray.findClosestGeoPoint(intersectionGeoPoint);
    }

    //endregion

    //region traceRay
    @Override
    public Color traceRay(Ray ray) {
       GeoPoint closestGeoPoint = findClosestIntersection(ray);
       if(closestGeoPoint == null)
       {
           return scene.background;
       }
       return calcColor(closestGeoPoint, ray);
    }

    @Override
    public Color traceRay(List<Ray> rayList) {
        List<Color> colorList = new ArrayList<>();
        //Color color = Color.BLACK;
        for (Ray ray:rayList) {
            colorList.add(traceRay(ray));}

        Color finalColor = Color.BLACK;
        Color[] colorArr = new Color[rayList.size()];
        colorArr = colorList.toArray(colorArr);
        return finalColor.add(colorArr).reduce(colorList.size());

        //return color.reduce(rayList.size());
        }

    //endregion

}
