package renderer;

import geometries.Plane;
import geometries.Sphere;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

/**
 * test soft shadow
 * @author Reut & Odelya.
 */
public class SoftShadowTest {

    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point(800,1100,77), new Vector(-800,-1100,0), new Vector(0,0,1)) //
            .setVPSize(200, 200).setVPDistance(1000)//
            .setRayTracer(new RayTracerBasic(scene));
    private Material standMaterial = new Material().setKD(0.5).setKS(0.5).setNShininess(30);
    private Color standColor = new Color(209, 179,155);
    private Color spCL = new Color(800,800,800); // stand test Color of Light

    @Test
    void testSoftShadow() {
        scene.geometries.add(new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,0))//
                .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)));

        scene.geometries.add(new Sphere(new Point(0,0,30), 30).setMaterial(standMaterial).setEmission(standColor));
        //endregion

        //region lights
        scene.lights.add(new SpotLight(spCL, new Point(-100,100,150), new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        //endregion

        ImageWriter imageWriter = new ImageWriter("softShadow test", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(9); //
        camera.writeToImage(); //
    }
}
