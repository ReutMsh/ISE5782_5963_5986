package lighting;

import primitives.Color;
import renderer.BlackBoard;

/**
 * abstract Class Light
 *
 * @author Reut and Odelya
 */
abstract class Light {

    private Color intensity;

    //region constructor
    /**
     * constructor
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }
    //endregion

    //region getter
    public Color getIntensity() {
        return intensity;
    }
    //endregion
}
