package miniProject;

import geometries.*;
import primitives.*;

import java.util.List;

public class ShapesScene extends Intersectable {

    private Material standMaterial;
    private Color standColor;
    private Geometries geometriesShapes;

    /**
     * stand constructor
     * build one stand(polygons) of the game
     * @param frontDown
     * @param hindDown
     * @param hindUp
     * @param frontUp
     * @param material
     * @param color
     */
    public ShapesScene(Point frontDown, Point hindDown, Point hindUp, Point frontUp, Material material, Color color){
        geometriesShapes = new Geometries();
        standColor = color;
        standMaterial = material;
        Polygon wall = new Polygon(frontDown, hindDown, hindUp, frontUp);
        //build normal
        Vector normal = wall.getNormal(frontDown);
        //find all point
        Point frontDown2 = frontDown.getPoint(normal, 15);
        Point hindDown2 = hindDown.getPoint(normal, 15);
        Point hindUp2 = hindUp.getPoint(normal, 15);
        Point frontUp2 = frontUp.getPoint(normal, 15);
        //2 wall
        geometriesShapes.add(wall.setEmission(standColor).setMaterial(standMaterial));
        geometriesShapes.add(new Polygon(frontDown2, hindDown2, hindUp2, frontUp2).setEmission(standColor).setMaterial(standMaterial));
        //base and top
        geometriesShapes.add(new Polygon(frontDown, frontDown2, hindDown2, hindDown).setEmission(standColor).setMaterial(standMaterial));
        geometriesShapes.add(new Polygon(frontUp, frontUp2, hindUp2, hindUp).setEmission(standColor).setMaterial(material));
        //front and hind
        geometriesShapes.add(new Polygon(frontDown, frontDown2, frontUp2, frontUp).setEmission(standColor).setMaterial(standMaterial));
        geometriesShapes.add(new Polygon(hindDown, hindDown2, hindUp2, hindUp).setEmission(standColor).setMaterial(standMaterial));

    }

    /**
     *  beads constructor
     *  Constructs some adjacent beads(spheres) in the vector direction
     * @param startPoint
     * @param direction
     * @param radius
     * @param material
     * @param color
     */
    public ShapesScene(Point startPoint, Vector direction, double radius, int countSpheres, Material material, Color color){
        geometriesShapes = new Geometries();
        standMaterial = material;
        standColor = color;
        Point nextCenter = startPoint.getPoint(direction,radius);

        for (int i = 0; i < countSpheres; i++, nextCenter = nextCenter.getPoint(direction, 2*radius)){
            geometriesShapes.add(new Sphere(nextCenter, radius).setMaterial(standMaterial).setEmission(standColor));
        }
    }

    /**
     * cylinder constructor
     * builds some parallel cylinders in the direction of the vector,
     * each starting at one of the points in the array, respectively.
     * @param direction
     * @param radius
     * @param length
     * @param material
     * @param color
     * @param startPoints
     */
    public ShapesScene(Vector direction, double radius , double length, Material material, Color color, Point... startPoints){
        geometriesShapes = new Geometries();
        standColor = color;
        standMaterial = material;

        for (Point startPoint: startPoints) {
            geometriesShapes.add(new Cylinder(new Ray(startPoint, direction), radius, length));
        }
    }
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return geometriesShapes.findGeoIntersections(ray,maxDistance);
    }
}
