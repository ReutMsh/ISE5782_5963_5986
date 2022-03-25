
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Camera Class and intersection with geometry
 * @author Odelya and Reut
 */
class rayConstructionTest {

    //region method
    /**
     * return num of intersection rays with the geometry
     * for all pixel in the view plan
     * @param camera
     * @param intersectable
     * @param nX
     * @param nY
     * @return int
     */
    int findNumOfIntersections(Camera camera , Intersectable intersectable , int nX, int nY)
    {
        int counterOfPoint = 0;
        for (int i=0; i<nY; i++)
        {
            for (int j=0;j<nX; j++)
            {
                List<Point> pointsIntersectionGeometry = intersectable.findIntersections(camera.constructRay(nX,nY,i,j));
                if(pointsIntersectionGeometry != null)
                    counterOfPoint += pointsIntersectionGeometry.size();
            }
        }
        return counterOfPoint;
    }
    //endregion

    //region test ray in Sphere
    /**
     * Test integration between {@link Camera#constructRay(int, int, int, int)} and {@link geometries.Sphere#findIntersections(Ray)}.
     */
    @Test
    void testRayConstructionSphere() {

        Camera camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1);
        camera.setVPSize(3,3);

        //TC1: the sphere after the plan
        Sphere sphere = new Sphere(new Point(0,0,-2.5), 1d);
        assertEquals(2, findNumOfIntersections(camera, sphere, 3,3) ,  "ERROR: the number of intersections is not the same in sphere , TC1");

        //TC2:The view plan intersection the sphere which is bigger than the plain
        sphere = new Sphere(new Point(0,0,-2.5), 2.5);
        assertEquals(18, findNumOfIntersections(camera, sphere, 3,3)  , "ERROR: the number of intersections is not the same in sphere , TC2");

        //TC3:The view plan intersection the sphere which is smaller than the plain
        sphere = new Sphere(new Point(0,0,-2), 2);
        assertEquals(10, findNumOfIntersections(camera, sphere, 3,3) ,  "ERROR: the number of intersections is not the same in sphere , TC3");

        //TC4:The view plan including in sphere
        sphere = new Sphere(new Point(0,0,-0.5), 4);
        assertEquals(9, findNumOfIntersections(camera, sphere, 3,3) ,  "ERROR: the number of intersections is not the same in sphere , TC4");

        //TC5: the sphere is behind the view plane
        sphere = new Sphere(new Point(0,0,1.5), 0.5);
        assertEquals(0, findNumOfIntersections(camera, sphere, 3,3) ,  "ERROR: the number of intersections is not the same in sphere , TC5");
    }

    //endregion

    // region test ray in Plan
    /**
     * Test integration between {@link Camera#constructRay(int, int, int, int)} and {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void testRayConstructionPlan() {

        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1);
        camera.setVPSize(3,3);

        //TC1: the Plan parallel to the view plan
        Plane plane = new Plane(new Point(0,0,-2),new Point(1,0,-2), new Point(0,1,-2));
        assertEquals(9, findNumOfIntersections(camera, plane, 3,3), "ERROR: the number of intersections is not the same in Plan , TC1");

        //TC2: the plan not parallel (diagonally) to the view plan, but all the ray intersection
        plane = new Plane(new Point(0,0,-2), new Point(-1.5, -1.5,-1), new Point(-1.5, -0.5, -1));
        assertEquals(9, findNumOfIntersections(camera, plane, 3,3), "ERROR: the number of intersections is not the same in Plan , TC2");

        //TC3: the plan not parallel (diagonally) to the view plan, and only 6 ray intersection
        plane = new Plane(new Point(0,0,-2), new Point(-0.5, -1.5, -1), new Point(-0.5, 1.5, -1));
        assertEquals(6, findNumOfIntersections(camera, plane, 3,3), "ERROR: the number of intersections is not the same in Plan , TC3");
    }
    //endregion

    //region test ray in Triangle
    /**
     * Test integration between {@link Camera#constructRay(int, int, int, int)} and {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    void testRayConstructionTriangle() {

        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1);
        camera.setVPSize(3,3);

        //TC1: the triangle parallel to the view plan and there is one ray intersection
        Triangle triangle = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(1, findNumOfIntersections(camera, triangle, 3,3), "ERROR: the number of intersections is not the same in Triangle , TC1");

        //TC2: the triangle parallel to the view plan and there are two ray intersection
        triangle = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(2, findNumOfIntersections(camera, triangle, 3,3), "ERROR: the number of intersections is not the same in Triangle , TC2");

    }
    //endregion

    //region test ray in Geometries
    @Test
    void testRayConstructionGeometries() {
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1);
        camera.setVPSize(3,3);

        Sphere sphere = new Sphere(new Point(0,0,-3), 1d);
        Plane plane = new Plane(new Point(0,0,-2),new Point(1,0,-2), new Point(0,1,-2));
        Triangle triangle = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));

        //TC1: 2 intersection with the sphere, 9 intersection with the plan, 2 intersection with the triangle.
        assertEquals(13, findNumOfIntersections(camera, new Geometries(sphere, plane, triangle), 3,3), "ERROR: the number of intersections is not the same in geometries , TC1");
    }
    //endregion
}