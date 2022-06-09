package miniProject;

import geometries.Plane;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;


/**
 * Unit tests for the picture of the project
 * @author Reut & Odelya
 */
public class miniProjectTest {

    /**
     * Create a scene - if desired with improvements of Soft Shadow
     * enter count of ray in method setSoftShadow
     * else enter only one ray
     */
    private Scene scene = new Scene("Test scene").setSoftShadow(81);
    
     private Camera camera = new Camera(new Point(800,1100,77), new Vector(-800,-1100,0), new Vector(0,0,1)) //
            .setVPSize(200, 200).setVPDistance(1000)//
            .setRayTracer(new RayTracerBasic(scene)).setMultiThreading(true);
     
    private Material material = new Material().setKD(0.5).setKS(0.5).setNShininess(30);
    private Color standColor = new Color(209, 179,155);
    private Color whiteColorLight = new Color(300,300,300);
    private Color yellowColorLight = new Color(700,400,400);
    private Point spPL = new Point(100,100,150);

    private Vector rightDirection = new Vector(-1,0,0);
    private Vector leftDirection = new Vector(1,0,0);

    /**
     * The picture with no the improvements of Anti-Aliasing
     * using with the methods:
     * {@link ShapesScene#ShapesScene(Point, Point, Point, Point, Material, Color)}.
     * {@link ShapesScene#ShapesScene(Point, Vector, double, int, Material, Color)}.
     * {@link ShapesScene#ShapesScene(Vector, double, double, Material, Color, Point...)}.
     * {@link ShapesScene#findIntersections(Ray)}.
     */
    @Test
    void testAbacus() {
        //region geometries

        //region stands
        ShapesScene leftStand = new ShapesScene(new Point(100,25,0), new Point(100,-25,0), //
                new Point(100,-25,150), new Point(100,0,150), material, standColor);

        ShapesScene rightStand = new ShapesScene(new Point(-85,25,0), new Point(-85,-25,0), //
                new Point(-85,-25,150), new Point(-85,0,150), material, standColor);
        //endregion

        //region cylinders
        ShapesScene cylinders = new ShapesScene(rightDirection, 2, 170, material, standColor, new Point(85,7,25),//
        new Point(85,3,50), new Point(85,-1.5,75), new Point(85,-6,100), new Point(85,-10.5,125));

        ShapesScene boldCylinders = new ShapesScene(rightDirection, 3, 170, material, standColor, new Point(85,-20,135));
        //endregion

        //region beads
        ShapesScene beadsLineOneLeft = new ShapesScene(new Point(85,7,25),rightDirection, 7, 5, material, new Color(84, 100, 125));
        ShapesScene beadsLineOneRight = new ShapesScene(new Point(-85,7,25),leftDirection, 7, 3, material, new Color(84, 100, 125));

        ShapesScene beadsLineTwoLeft = new ShapesScene(new Point(85,3,50),rightDirection, 7, 2, material, new Color(197, 199, 196));
        ShapesScene beadsLineTwoRight = new ShapesScene(new Point(-85,3,50),leftDirection, 7, 7, material, new Color(197, 199, 196));

        ShapesScene beadsLineThreeLeft = new ShapesScene(new Point(85,-1.5,75),rightDirection, 7, 5, material, new Color(182, 195, 183));
        ShapesScene beadsLineThreeRight = new ShapesScene(new Point(-85,-1.5,75),leftDirection, 7, 5, material, new Color(182, 195, 183));

        ShapesScene beadsLineFourLeft = new ShapesScene(new Point(85,-6,100),rightDirection, 7, 3, material, new Color(196, 138, 90));
        ShapesScene beadsLineFourRight = new ShapesScene(new Point(-85,-6,100),leftDirection, 7, 8, material, new Color(196, 138, 90));

        ShapesScene beadsLineFiveLeft = new ShapesScene(new Point(85,-10.5,125),rightDirection, 7, 8, material, new Color(218, 194, 182));
        ShapesScene beadsLineFiveRight = new ShapesScene(new Point(-85,-10.5,125),leftDirection, 7, 2, material, new Color(218, 194, 182));

        //endregion

        //region add to geometries
        scene.geometries.add(new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,0))//
                .setMaterial(new Material().setKR(new Double3(0.2))).setEmission(new Color(20,20,20)),
                new Plane(new Point(0,-200,1), new Point(1,-200,0), new Point(0,-200,0))//
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300)).setEmission(new Color(0,0,0)));

        scene.geometries.add(leftStand, rightStand, beadsLineOneLeft, beadsLineOneRight, beadsLineTwoLeft, beadsLineTwoRight,//
                beadsLineThreeLeft, beadsLineThreeRight, beadsLineFourLeft, beadsLineFourRight, beadsLineFiveLeft, beadsLineFiveRight,//
                cylinders, boldCylinders);
        //endregion

        //endregion

        //region lights
        scene.lights.add(new SpotLight(whiteColorLight, new Point(-100,100,150), new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        scene.lights.add(new PointLight(whiteColorLight,spPL).setKL(0.05).setKQ(0.0001));
        scene.lights.add(new PointLight(whiteColorLight, new Point(0,0,200)).setKL(0.05));
        //endregion

        ImageWriter imageWriter = new ImageWriter("standAbacus test", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage(); //
    }

    /**
     * The picture with the improvements of Anti-Aliasing
     */
    @Test
    void testAbacusWithAntiAliasing() {

        //region geometries

        //region stands
        ShapesScene leftStand = new ShapesScene(new Point(100,25,0), new Point(100,-25,0), //
                new Point(100,-25,150), new Point(100,0,150), material, standColor);

        ShapesScene rightStand = new ShapesScene(new Point(-85,25,0), new Point(-85,-25,0), //
                new Point(-85,-25,150), new Point(-85,0,150), material, standColor);
        //endregion

        //region cylinders
        ShapesScene cylinders = new ShapesScene(rightDirection, 2, 170, material, standColor, new Point(85,7,25),//
                new Point(85,3,50), new Point(85,-1.5,75), new Point(85,-6,100), new Point(85,-10.5,125));

        ShapesScene boldCylinders = new ShapesScene(rightDirection, 3, 170, material, standColor, new Point(85,-20,135));
        //endregion

        //region beads
        ShapesScene beadsLineOneLeft = new ShapesScene(new Point(85,7,25),rightDirection, 7, 5, material, new Color(84, 100, 125));
        ShapesScene beadsLineOneRight = new ShapesScene(new Point(-85,7,25),leftDirection, 7, 3, material, new Color(84, 100, 125));

        ShapesScene beadsLineTwoLeft = new ShapesScene(new Point(85,3,50),rightDirection, 7, 2, material, new Color(197, 199, 196));
        ShapesScene beadsLineTwoRight = new ShapesScene(new Point(-85,3,50),leftDirection, 7, 7, material, new Color(197, 199, 196));

        ShapesScene beadsLineThreeLeft = new ShapesScene(new Point(85,-1.5,75),rightDirection, 7, 5, material, new Color(182, 195, 183));
        ShapesScene beadsLineThreeRight = new ShapesScene(new Point(-85,-1.5,75),leftDirection, 7, 5, material, new Color(182, 195, 183));

        ShapesScene beadsLineFourLeft = new ShapesScene(new Point(85,-6,100),rightDirection, 7, 3, material, new Color(196, 138, 90));
        ShapesScene beadsLineFourRight = new ShapesScene(new Point(-85,-6,100),leftDirection, 7, 8, material, new Color(196, 138, 90));

        ShapesScene beadsLineFiveLeft = new ShapesScene(new Point(85,-10.5,125),rightDirection, 7, 8, material, new Color(218, 194, 182));
        ShapesScene beadsLineFiveRight = new ShapesScene(new Point(-85,-10.5,125),leftDirection, 7, 2, material, new Color(218, 194, 182));

        //endregion

        //region add to geometries
        scene.geometries.add(new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,0))//
                .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)));

        scene.geometries.add(leftStand, rightStand, beadsLineOneLeft, beadsLineOneRight, beadsLineTwoLeft, beadsLineTwoRight,//
                beadsLineThreeLeft, beadsLineThreeRight, beadsLineFourLeft, beadsLineFourRight, beadsLineFiveLeft, beadsLineFiveRight,//
                cylinders, boldCylinders);
        //endregion

        //endregion

        //region lights
        scene.lights.add(new SpotLight(whiteColorLight, new Point(-100,100,150), new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        scene.lights.add(new PointLight(whiteColorLight,spPL).setKL(0.05).setKQ(0.0001));
        scene.lights.add(new PointLight(whiteColorLight, new Point(0,0,200)).setKL(0.05));
        scene.lights.add(new SpotLight(yellowColorLight, new Point(-40,0,200), new Vector(0,0,-1)).setKL(0.3));
        scene.lights.add(new DirectionalLight(new Color(40,20,20), new Vector(0,0,-1)));
        //endregion

        ImageWriter imageWriter = new ImageWriter("abacus with antiAliasing test", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(9); //
        camera.writeToImage(); //
    }
}
