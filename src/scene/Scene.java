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
    /**
     * count of shadow ray (if > 1 -> soft shadow on).
     */
    public double softShadow = 1;

    //region constructor
    /**
     * constructor
     * @param name - name of scene.
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
     * builder set background
     * @return this (builder).
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * builder set ambientLight
     * @return this (builder).
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * builder set geometries
     * @return this (builder).
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * builder set lights
     * @return this (builder).
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * builder set soft shadow
     * if the shadow will be soft.
     * @param softShadow - count of ray (if > 1 -> soft shadow on)
     * @return this (builder).
     */
    public Scene setSoftShadow(double softShadow) {
        if(softShadow <= 0)
            this.softShadow = 1;
        else
            this.softShadow = softShadow;
        return this;
    }
    //endregion

}
