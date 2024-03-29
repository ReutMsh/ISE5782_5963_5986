package renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;
import static java.awt.Color.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */


public class LightsTests {
    private Scene scene1 = new Scene("Test scene");
    private Scene scene2 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
    private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150) //
            .setVPDistance(1000);
    private Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(200, 200) //
            .setVPDistance(1000);

    private Point[] p = { // The Triangles' vertices:
            new Point(-110, -110, -150), // the shared left-bottom
            new Point(95, 100, -150), // the shared right-top
            new Point(110, -110, -150), // the right-bottom
            new Point(-75, 78, 100) }; // the left-top
    private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
    private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
    private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
    private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
    private Material material = new Material().setKD(0.5).setKS(0.5).setNShininess(300);
    private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
    private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);

    private Geometry polygon1 = new Polygon(p[0], p[1], p[2]).setMaterial(material);
    private Geometry polygon2 = new Polygon(p[0], p[1], p[3]).setMaterial(material);

    private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(9); //
        camera1.writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new PointLight(spCL, spPL).setKL(0.001).setKQ(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(9); //
        camera1.writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a spotlight
     */
    @Test
    public void sphereSpot() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setKL(0.001).setKQ(0.0001));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(9); //
        camera1.writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a multi light
     */
    @Test
    void testSphereMultiLight() {
        scene1.geometries.add(sphere);
        //green spot
        Color spSLGSpot = new Color(377, 448, 367); // Sphere test Color of Light
        scene1.lights.add(new SpotLight(spSLGSpot, spPL, new Vector(1, 1, -0.5)).setKL(0.001).setKQ(0.0001));
        //purple spot
        Color spSLPSpot = new Color(364, 0, 364); // Sphere test Color of Light
        scene1.lights.add(new SpotLight(spSLPSpot, new Point(0, -100, 5), new Vector(-11.25, 51.28, -55)).setKL(0.001).setKQ(0.0001));
        //red point
        scene1.lights.add(new PointLight(new Color(464, 1000, 528), new Point(0, 60, 50)).setKL(0.001).setKQ(0.0002));

        //orange direct
        scene1.lights.add(new DirectionalLight(new Color(255, 128, 0), new Vector(47, 17, 139)));

        ImageWriter imageWriter = new ImageWriter("lightSphereMultiLight", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(9); //
        camera1.writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new DirectionalLight(trCL, trDL));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage(); //
        camera2.writeToImage(); //
    }

    /**
     * Produce a picture of a two polygon lighted by a directional light
     */
    @Test
    public void polygonsDirectional() {
        scene2.geometries.add(polygon1, polygon2);
        scene2.lights.add(new DirectionalLight(trCL, trDL));

        ImageWriter imageWriter = new ImageWriter("lightPolygonsDirectional", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage(); //
        camera2.writeToImage(); //
    }

    /**
     * Produce a picture of a two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new PointLight(trCL, trPL).setKL(0.001).setKQ(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage(); //
        camera2.writeToImage(); //
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light
     */
    @Test
    public void trianglesSpot() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trCL, trPL, trDL).setKL(0.001).setKQ(0.0001));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage(); //
        camera2.writeToImage(); //
    }

    /**
     * Produce a picture of  two triangles lighted by a multi light
     */
    @Test
    void testTriangleMultiLight() {
        scene2.geometries.add(triangle1, triangle2);

        //green spot
        Color spSLGSpot = new Color(377, 448, 367); // Sphere test Color of Light
        scene2.lights.add(new SpotLight(spSLGSpot, spPL, new Vector(1, 1, -0.5)).setKL(0.001).setKQ(0.0001));
        //purple spot
        Color spSLPSpot = new Color(364, 0, 364); // Sphere test Color of Light
        scene2.lights.add(new SpotLight(spSLPSpot, new Point(0, -100, 5), new Vector(-11.25, 51.28, -55)).setKL(0.001).setKQ(0.0001));
        //red point
        scene2.lights.add(new PointLight(new Color(464, 1000, 528), new Point(0, 60, 50)).setKL(0.001).setKQ(0.0002));

        //orange direct
        scene2.lights.add(new DirectionalLight(new Color(255, 128, 0), new Vector(47, 17, 139)));

        ImageWriter imageWriter = new ImageWriter("lightTriangleMultiLight", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage(); //
        camera2.writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a narrow spot light
     */
    @Test
    public void sphereSpotSharp() {
        scene1.geometries.add(sphere);
        scene1.lights
                .add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5))/*.setNarrowBeam(10)*/.setKL(0.001).setKQ(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage();//
    }

    /**
     * Produce a picture of two triangles lighted by a narrow spotlight
     */
    @Test
    public void trianglesSpotSharp() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trCL, trPL, trDL)/*.setNarrowBeam(10)*/.setKL(0.001).setKQ(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage(); //
        camera2.writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a narrow spotlight
     */
    @Test
    public void polygonsSpotSharp() {
        scene2.geometries.add(polygon1, polygon2);
        scene2.lights.add(new SpotLight(trCL, trPL, trDL)/*.setNarrowBeam(10)*/.setKL(0.001).setKQ(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightPolygonsSpotSharp", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage(); //
        camera2.writeToImage(); //
    }
}

