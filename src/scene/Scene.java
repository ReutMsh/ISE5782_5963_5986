package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

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
    public List<LightSource> lights =  new LinkedList<>();

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

    /**
     * set background
     * @param background
     * @return Scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set ambientLight
     * @param ambientLight
     * @return Scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set geometries
     * @param geometries
     * @return Scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     *set lights
     * @param lights
     * @return Scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    //endregion

}
