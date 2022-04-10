package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Class AmbientLight
 *extends from classes Light
 * @author Reut and Odelya
 */
public class AmbientLight extends Light {

    //region constructors
    /**
     * constructor (use super constructor)
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA  , Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Default constructor (use super constructor)
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
    //endregion



}
