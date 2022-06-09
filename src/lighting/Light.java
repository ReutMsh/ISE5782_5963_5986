package lighting;

import primitives.Color;

/**
 * abstract Class Light
 * define general physic light.
 * @author Reut and Odelya
 */
abstract class Light {

    private Color intensity;

    //region constructor
    /**
     * constructor
     * @param intensity - the light intensity.
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
