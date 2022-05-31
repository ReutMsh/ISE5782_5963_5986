package miniProject;

import geometries.Plane;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;



public class miniProjectOneTest {

    private Scene scene = new Scene("Test scene");
    //private Camera camera = new Camera(new Point(900,1500,300), new Vector(-920,-1500,-250), new Vector(0,-250,1500)) //
      //      .setVPSize(200, 200).setVPDistance(1000)//
        //    .setRayTracer(new RayTracerBasic(scene));

    private Camera camera = new Camera(new Point(800,1100,77), new Vector(-800,-1100,0), new Vector(0,0,1)) //
            .setVPSize(200, 200).setVPDistance(1000)//
            .setRayTracer(new RayTracerBasic(scene));

    //private Camera camera = new Camera(new Point(0,1500,77), new Vector(0,-1,0), new Vector(0,0,1)) //
      //      .setVPSize(200, 200).setVPDistance(1000)//
        //    .setRayTracer(new RayTracerBasic(scene));

    //private Camera camera = new Camera(new Point(-1500,500,77), new Vector(1500,-500,0), new Vector(0,0,1)) //
      //      .setVPSize(200, 200).setVPDistance(1000)//
        //    .setRayTracer(new RayTracerBasic(scene));

    private Material standMaterial = new Material().setKD(0.5).setKS(0.5).setNShininess(30);
    private Color standColor = new Color(209, 179,155);
    private Color spCL = new Color(700,400,400); // stand test Color of Light
    private Point spPL = new Point(100,100,150); // stand test Position of Light

    private Vector rightDirection = new Vector(-1,0,0);
    private Vector leftDirection = new Vector(1,0,0);

    @Test
    void testStandWithBeads() {
        //region geometries

        //region stands
        ShapesScene leftStand = new ShapesScene(new Point(100,25,0), new Point(100,-25,0), //
                new Point(100,-25,150), new Point(100,0,150), standMaterial, standColor);

        ShapesScene rightStand = new ShapesScene(new Point(-85,25,0), new Point(-85,-25,0), //
                new Point(-85,-25,150), new Point(-85,0,150), standMaterial, standColor);
        //endregion

        //region cylinders
        ShapesScene cylinders = new ShapesScene(rightDirection, 2, 170, standMaterial, standColor, new Point(85,7,25),//
        new Point(85,3,50), new Point(85,-1.5,75), new Point(85,-6,100), new Point(85,-10.5,125));

        ShapesScene boldCylinders = new ShapesScene(rightDirection, 3, 170, standMaterial, standColor, new Point(85,-20,135));
        //endregion

        //region beads
        ShapesScene beadsLineOneLeft = new ShapesScene(new Point(85,7,25),rightDirection, 7, 5, standMaterial, new Color(84, 100, 125));
        ShapesScene beadsLineOneRight = new ShapesScene(new Point(-85,7,25),leftDirection, 7, 3, standMaterial, new Color(84, 100, 125));

        ShapesScene beadsLineTwoLeft = new ShapesScene(new Point(85,3,50),rightDirection, 7, 2, standMaterial, new Color(197, 199, 196));
        ShapesScene beadsLineTwoRight = new ShapesScene(new Point(-85,3,50),leftDirection, 7, 7, standMaterial, new Color(197, 199, 196));

        ShapesScene beadsLineThreeLeft = new ShapesScene(new Point(85,-1.5,75),rightDirection, 7, 5, standMaterial, new Color(182, 195, 183));
        ShapesScene beadsLineThreeRight = new ShapesScene(new Point(-85,-1.5,75),leftDirection, 7, 5, standMaterial, new Color(182, 195, 183));

        ShapesScene beadsLineFourLeft = new ShapesScene(new Point(85,-6,100),rightDirection, 7, 3, standMaterial, new Color(196, 138, 90));
        ShapesScene beadsLineFourRight = new ShapesScene(new Point(-85,-6,100),leftDirection, 7, 8, standMaterial, new Color(196, 138, 90));

        ShapesScene beadsLineFiveLeft = new ShapesScene(new Point(85,-10.5,125),rightDirection, 7, 8, standMaterial, new Color(218, 194, 182));
        ShapesScene beadsLineFiveRight = new ShapesScene(new Point(-85,-10.5,125),leftDirection, 7, 2, standMaterial, new Color(218, 194, 182));

        //endregion

        scene.geometries.add(new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,0))//
                .setMaterial(new Material().setKR(new Double3(0.2))).setEmission(new Color(20,20,20)),
                new Plane(new Point(0,-200,1), new Point(1,-200,0), new Point(0,-200,0))//
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300)).setEmission(new Color(0,0,0)));

        scene.geometries.add(leftStand, rightStand, beadsLineOneLeft, beadsLineOneRight, beadsLineTwoLeft, beadsLineTwoRight,//
                beadsLineThreeLeft, beadsLineThreeRight, beadsLineFourLeft, beadsLineFourRight, beadsLineFiveLeft, beadsLineFiveRight,//
                cylinders, boldCylinders);
        //endregion

        //region lights
        scene.lights.add(new SpotLight(spCL, new Point(-100,100,150), new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        scene.lights.add(new PointLight(spCL,spPL).setKL(0.05).setKQ(0.0001));
        scene.lights.add(new PointLight(spCL, new Point(0,0,200)).setKL(0.05));
        //endregion

        ImageWriter imageWriter = new ImageWriter("standWithBeads test", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage(); //
    }

    @Test
    void testStandWithBeadsAntiAliasing() {
        //region geometries

        //region stands
        ShapesScene leftStand = new ShapesScene(new Point(100,25,0), new Point(100,-25,0), //
                new Point(100,-25,150), new Point(100,0,150), standMaterial, standColor);

        ShapesScene rightStand = new ShapesScene(new Point(-85,25,0), new Point(-85,-25,0), //
                new Point(-85,-25,150), new Point(-85,0,150), standMaterial, standColor);
        //endregion

        //region cylinders
        ShapesScene cylinders = new ShapesScene(rightDirection, 2, 170, standMaterial, standColor, new Point(85,7,25),//
                new Point(85,3,50), new Point(85,-1.5,75), new Point(85,-6,100), new Point(85,-10.5,125));

        ShapesScene boldCylinders = new ShapesScene(rightDirection, 3, 170, standMaterial, standColor, new Point(85,-20,135));
        //endregion

        //region beads
        ShapesScene beadsLineOneLeft = new ShapesScene(new Point(85,7,25),rightDirection, 7, 5, standMaterial, new Color(84, 100, 125));
        ShapesScene beadsLineOneRight = new ShapesScene(new Point(-85,7,25),leftDirection, 7, 3, standMaterial, new Color(84, 100, 125));

        ShapesScene beadsLineTwoLeft = new ShapesScene(new Point(85,3,50),rightDirection, 7, 2, standMaterial, new Color(197, 199, 196));
        ShapesScene beadsLineTwoRight = new ShapesScene(new Point(-85,3,50),leftDirection, 7, 7, standMaterial, new Color(197, 199, 196));

        ShapesScene beadsLineThreeLeft = new ShapesScene(new Point(85,-1.5,75),rightDirection, 7, 5, standMaterial, new Color(182, 195, 183));
        ShapesScene beadsLineThreeRight = new ShapesScene(new Point(-85,-1.5,75),leftDirection, 7, 5, standMaterial, new Color(182, 195, 183));

        ShapesScene beadsLineFourLeft = new ShapesScene(new Point(85,-6,100),rightDirection, 7, 3, standMaterial, new Color(196, 138, 90));
        ShapesScene beadsLineFourRight = new ShapesScene(new Point(-85,-6,100),leftDirection, 7, 8, standMaterial, new Color(196, 138, 90));

        ShapesScene beadsLineFiveLeft = new ShapesScene(new Point(85,-10.5,125),rightDirection, 7, 8, standMaterial, new Color(218, 194, 182));
        ShapesScene beadsLineFiveRight = new ShapesScene(new Point(-85,-10.5,125),leftDirection, 7, 2, standMaterial, new Color(218, 194, 182));

        //endregion

        scene.geometries.add(new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,0))//
                .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)));

        scene.geometries.add(leftStand, rightStand, beadsLineOneLeft, beadsLineOneRight, beadsLineTwoLeft, beadsLineTwoRight,//
                beadsLineThreeLeft, beadsLineThreeRight, beadsLineFourLeft, beadsLineFourRight, beadsLineFiveLeft, beadsLineFiveRight,//
                cylinders, boldCylinders);
        //endregion

        //region lights
        scene.lights.add(new SpotLight(spCL, new Point(-100,100,150), new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        scene.lights.add(new PointLight(spCL,spPL).setKL(0.05).setKQ(0.0001));
        scene.lights.add(new PointLight(spCL, new Point(0,0,200)).setKL(0.05));
        //endregion

        ImageWriter imageWriter = new ImageWriter("standWithBeadsAntiAliasing test", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(9); //
        camera.writeToImage(); //
    }
}
