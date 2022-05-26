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
   /* private Camera camera = new Camera(new Point(-1000, 1500, 50), new Vector(1080, -1500, 0), new Vector(0, 0, 1)) //
            .setVPSize(200, 200).setVPDistance(1000) //
            .setRayTracer(new RayTracerBasic(scene)).moveAroundVUp(Math.toRadians(0));
*/
    /*private Camera camera = new Camera(new Point(0, 0, 1500), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
            .setVPSize(200, 200).setVPDistance(1000) //
            .setRayTracer(new RayTracerBasic(scene)).moveAroundVUp(Math.toRadians(0));
            */
    /*private Camera camera = new Camera(new Point(-1000,2000,500), new Vector(1080,-1500,-450), new Vector(1500,1080,0)) //
           .setVPSize(200, 200).setVPDistance(1000) //
           .setRayTracer(new RayTracerBasic(scene)).moveAroundVTo(Math.toRadians(-90)).moveAroundVUp(Math.toRadians(10)).moveAroundVRight(Math.toRadians(-2.5));
           */
   private Camera camera = new Camera(new Point(900,1500,300), new Vector(-920,-1500,-250), new Vector(0,-250,1500)) //
           .setVPSize(200, 200).setVPDistance(1000) //
           .setRayTracer(new RayTracerBasic(scene)).moveAroundVTo(Math.toRadians(0)).moveAroundVUp(Math.toRadians(0)).moveAroundVRight(Math.toRadians(-1));
    private Material standMaterial = new Material().setKD(0.5).setKS(0.5).setNShininess(30);
    private Color standColor = new Color(209, 179,155);
    private Color spCL = new Color(700,400,400); // stand test Color of Light
    private Point spPL = new Point(100,100,150); // stand test Position of Light

    @Test
    void testBuildStand() {
        Geometries stand1 = BuildStand(new Point(100,25,0), new Point(100,-25,0), //
                 new Point(100,-25,140), new Point(100,0,140), standMaterial, standColor);

        Geometries stand2 = BuildStand(new Point(-100,25,0), new Point(-100,-25,0), //
                new Point(-100,-25,140), new Point(-100,0,140), standMaterial, standColor);

        scene.geometries.add(new Sphere(new Point(0, 0, 200), 30d).setEmission(new Color(white)) //
                .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(30).setKT(new Double3(0.4))));

        scene.geometries.add(new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,0))//
               .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)));
        scene.geometries.add(stand1, stand2);

        //scene.lights.add(new SpotLight(spCL, spPL, new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        scene.lights.add(new SpotLight(spCL, new Point(-100,100,150), new Vector(130, -100, -80)).setKL(0.001).setKQ(0.0001));
        scene.lights.add(new PointLight(spCL,spPL).setKL(0.05).setKQ(0.0001));
        //scene.lights.add(new PointLight(spCL,new Point(0,100,150)).setKL(0.05).setKQ(0.0001));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(0, 0, 200), new Vector(0, 0, -1)) //
                .setKL(4E-5).setKQ(2E-7));
        ImageWriter imageWriter = new ImageWriter("stand test", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage(); //
    }

    //region build scene

    /**
     * build one stand of the game
     * return list of polygons
     * @param frontDown
     * @param hindDown
     * @param hindUp
     * @param frontUp
     * @param material
     * @param color
     * @return List<Intersectable>
     */
    private Geometries BuildStand(Point frontDown, Point hindDown, Point hindUp, Point frontUp, Material material, Color color){
        Geometries stand = new Geometries();

        Polygon wall = new Polygon(frontDown, hindDown, hindUp, frontUp);
        //build normal
        Vector normal = wall.getNormal(frontDown);
        //find all point
        Point frontDown2 = frontDown.getPoint(normal, 15);
        Point hindDown2 = hindDown.getPoint(normal, 15);
        Point hindUp2 = hindUp.getPoint(normal, 15);
        Point frontUp2 = frontUp.getPoint(normal, 15);
        //2 wall
        stand.add(wall.setEmission(color).setMaterial(material));
        stand.add(new Polygon(frontDown2, hindDown2, hindUp2, frontUp2).setEmission(color).setMaterial(material));
        //base and top
        stand.add(new Polygon(frontDown, frontDown2, hindDown2, hindDown).setEmission(color).setMaterial(material));
        stand.add(new Polygon(frontUp, frontUp2, hindUp2, hindUp).setEmission(color).setMaterial(material));
        //front and hind
        stand.add(new Polygon(frontDown, frontDown2, frontUp2, frontUp).setEmission(color).setMaterial(material));
        stand.add(new Polygon(hindDown, hindDown2, hindUp2, hindUp).setEmission(color).setMaterial(material));

        return stand;
    }

    //endregion
}
