package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class DirectionalLight
 * extends Light implements LightSource
 * @author Reut and odelya
 */
public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    //region constructor

    /**
     *constructor (use super constructor)
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    //endregion

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
