package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import renderer.BlackBoard;

/**
 * interface LightSource
 * define all the light (include ambientLight)
 * @author Reut and odelya
 */
public interface LightSource {

    /**
     * return the intensity
     * @param p - the point to get intensity
     * @return the intensity in specific point.
     */
    Color getIntensity(Point p);

    /**
     * return the director(vector) between light and point on the geometry.
     * the vector is normalised
     * @param p - the point to get direction.
     * @return the director(vector) between light and point.
     */
    Vector getL(Point p);

    /**
     * return the distance between point and lightSource
     * @param point - the point to get distance from the light.
     * @return distance between point and lightSource.
     */
     double getDistance(Point point);

    /**
     * build the blackBoard in point
     * the BlackBoard defines area per light.
     * @param point - the blackBoard is per point.
     * @return the blackBoard per specific point.
     */
    BlackBoard getBlackBoard(Point point);
}
