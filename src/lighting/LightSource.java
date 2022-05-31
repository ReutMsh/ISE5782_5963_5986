package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import renderer.BlackBoard;

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
    public double getDistance(Point point);

    /**
     * build the blackBoard in point
     * the BlackBoard defines area per light.
     * @param point
     * @return BlackBoard
     */
    public BlackBoard getBlackBoard(Point point);
}
