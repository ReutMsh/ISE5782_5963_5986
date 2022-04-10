package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * PDS Class Scene
 *
 * @author Reut and Odelya
 */
public class Scene {

    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    //region constructor
    /**
     * constructor
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        this.background= Color.BLACK;
        this.ambientLight= new AmbientLight();
        this.geometries=new Geometries();
    }
    //endregion

    //region setters
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
    //endregion

}
