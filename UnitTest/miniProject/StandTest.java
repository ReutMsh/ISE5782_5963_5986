package miniProject;

import geometries.*;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.BLUE;
import static java.awt.Color.white;

public class StandTest {

    private Scene scene = new Scene("Test scene");

   private Camera camera = new Camera(new Point(900,1500,300), new Vector(-920,-1500,-250), new Vector(0,-250,1500)) //
           .setVPSize(200, 200).setVPDistance(1000) //
           .setRayTracer(new RayTracerBasic(scene)).moveAroundVTo(Math.toRadians(0)).moveAroundVUp(Math.toRadians(0)).moveAroundVRight(Math.toRadians(-1));
    private Material standMaterial = new Material().setKD(0.5).setKS(0.5).setNShininess(30);
    private Color standColor = new Color(209, 179,155);
    private Color spCL = new Color(700,400,400); // stand test Color of Light
    private Point spPL = new Point(100,100,150); // stand test Position of Light

    @Test
    void testBuildStand() {
        ShapesScene stand1 = new ShapesScene(new Point(100,25,0), new Point(100,-25,0), //
                new Point(100,-25,140), new Point(100,0,140), standMaterial, standColor);

        ShapesScene stand2 = new ShapesScene(new Point(-100,25,0), new Point(-100,-25,0), //
                new Point(-100,-25,140), new Point(-100,0,140), standMaterial, standColor);


        scene.geometries.add(new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,0))//
               .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)));
        scene.geometries.add(stand1, stand2);

        scene.lights.add(new SpotLight(spCL, new Point(-100,100,150), new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        scene.lights.add(new PointLight(spCL,spPL).setKL(0.05).setKQ(0.0001));
        scene.lights.add(new PointLight(spCL, new Point(0,0,200)).setKL(0.05));

        ImageWriter imageWriter = new ImageWriter("stand test", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage(); //
    }


}
