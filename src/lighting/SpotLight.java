package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class SpotLight
 * extends PointLight
 * @author Reut and odelya
 */
public class SpotLight extends PointLight{

    private Vector direction;

    //region constructor

    /**
     * constructor (use super constructor)
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    //endregion


    @Override
    public Color getIntensity(Point p) {
        double maxDir = Math.max(0,direction.dotProduct(getL(p)));
        return super.getIntensity(p).scale(maxDir);
    }
}
