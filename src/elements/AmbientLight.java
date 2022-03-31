package elements;

import primitives.Color;
import primitives.Double3;

/**
 * Class AmbientLight
 *
 * @author Reut and Odelya
 */
public class AmbientLight {

    private Color intensity;

    //region constructors
    /**
     * constructor
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA  , Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * Default constructor
     */
    public AmbientLight() {
        this.intensity = Color.BLACK;
    }
    //endregion

    //region get
    public Color getIntensity() {
        return intensity;
    }
    //endregion

}
