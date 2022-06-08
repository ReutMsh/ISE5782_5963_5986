package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import renderer.BlackBoard;

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
     * @param intensity - the light intensity.
     * @param position  - the light point.
     * @param direction  - the light direction.
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

    @Override
    public BlackBoard getBlackBoard(Point point) {
        return new BlackBoard(ALFA, getDistance(point), getL(point).scale(-1), position);
    }
}
