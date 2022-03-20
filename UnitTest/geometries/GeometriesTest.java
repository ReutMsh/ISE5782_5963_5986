package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Geometries class
 * @author Reut & Odelya
 */
class GeometriesTest {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        Plane pl = new Plane(new Point(0,0,2), new Point(1,0,2), new Point(0,1,2));
        Sphere sphere = new Sphere( new Point(1, 0, 0), 1d);
        Triangle triangle = new Triangle(new Point(0,-1,2), new Point(3,3,2), new Point(-3,3,2));
        Ray ray = new Ray(new Point(2,0,0), new Vector(-2,-2,2));
        // ============ Equivalence Partitions Tests ==============
        //TC01: Only some of the lines are intersection
        Geometries geometries = new Geometries(pl,sphere, triangle);
        //אם מוחזר null
        assertEquals(geometries.findIntersections(ray).size(), 2, "ERROR: Wrong number of points intersects the geometries " +
                "when only some of the lines are intersection");

        // =============== Boundary Values Tests ==================

        //TC11: No geometry is intersection
        ray = new Ray(new Point(0,-2,2), new Vector(0,2,-0.5));
        assertNull(geometries.findIntersections(ray), "ERROR: Wrong number of points intersects the geometries " +
                "when no geometry is intersection");

        //TC12: only one geometry is intersection
        ray = new Ray(new Point(0,-4,3), new Vector(0,4,-1.5));
        assertEquals(geometries.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the geometries " +
                "when only one geometry is intersection");
        //TC13:all the geometries are intersection
        ray = new Ray(new Point(0.5,0,3), new Vector(0,0,-6));
        assertEquals(geometries.findIntersections(ray).size(), 4, "ERROR: Wrong number of points intersects the geometries " +
                "when all the geometries are intersection");
        //TC14: the geometries are empty
        geometries = new Geometries();
        assertNull(geometries.findIntersections(ray), "ERROR: Wrong number of points intersects the geometries " +
                "when the geometries are empty");




    }
}