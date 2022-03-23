
import geometries.Intersectable;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class rayConstructionTest {


    int numOfIntersections(Camera camera , Intersectable intersectable , int nX, int nY)
    {
        int counterOfPoint=0;
        for (int i=0; i<nY; i++)
        {
            for (int j=0;j<nX; j++)
            {
                List<Point> pointList=intersectable.findIntersections(camera.constructRay(nX,nY,i,j));
                counterOfPoint += pointList.size();
            }
        }
        return counterOfPoint;
    }


    @Test
    void testRayConstructionSphere() {

        //TC1: the sphere after the plan
        Camera camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0)).setVPDistance(1);
        camera.setVPSize(3,3);
        Sphere sphere = new Sphere(new Point(0,0,-2.5), 1d);
        assertEquals(numOfIntersections(camera, sphere, 3,3) , 2, "ERROR: the number of intersections is not the same in sphere , TC01");

        //TC2:The view plan intersection the sphere which is bigger than the plain
        sphere = new Sphere(new Point(0,0,-2.5), 2.5);
        assertEquals(numOfIntersections(camera, sphere, 3,3) , 18, "ERROR: the number of intersections is not the same in sphere , TC02");

        //TC03:The view plan intersection the sphere which is smaller than the plain
        sphere = new Sphere(new Point(0,0,-2), 2);
        assertEquals(numOfIntersections(camera, sphere, 3,3) , 10, "ERROR: the number of intersections is not the same in sphere , TC03");

        //TC04:The view plan including in sphere
        sphere = new Sphere(new Point(0,0,-0.5), 4);
        assertEquals(numOfIntersections(camera, sphere, 3,3) , 9, "ERROR: the number of intersections is not the same in sphere , TC04");

        //TC05: the sphere is behind the view plane
        sphere = new Sphere(new Point(0,0,1.5), 0.5);
        assertEquals(numOfIntersections(camera, sphere, 3,3) , 0, "ERROR: the number of intersections is not the same in sphere , TC05");
    }
}