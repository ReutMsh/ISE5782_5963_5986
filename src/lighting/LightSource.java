package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface LightSource
 * define all the light
 * @author Reut and odelya
 */
public interface LightSource {
    /**
     * return the intensity
     * @param p
     * @return Color
     */
    public Color getIntensity(Point p);

    /**
     * return the director(vector) between light and point on the geometry
     * the vector is normalised
     * @param p
     * @return Vector
     */
    public Vector getL(Point p);

    /**
     *return the distance between point and lightSource
     * @param point
     * @return double
     */
    double getDistance(Point point);
}
