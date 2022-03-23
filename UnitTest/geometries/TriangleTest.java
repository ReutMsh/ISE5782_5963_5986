package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 * @author Reut & Odelya
 */
class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(0,-1,2), new Point(3,3,2), new Point(-3,3,2));

        //all the tests are only if the ray intersects with the plane

        // ============ Equivalence Partitions Tests ==============
        //TC01: the point inside the triangle
        Ray ray = new Ray(new Point(2,0,0), new Vector(-1, 2,2));
        //אם מוחזר null
        assertEquals(triangle.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the sphere " +
        "when the point Inside the triangle");

        assertEquals(triangle.findIntersections(ray).get(0), new Point(1,2,2), "ERROR: Wrong number of points intersects the sphere " +
        "when the point Inside the triangle");

        //TC02: the point outside against edge
        ray = new Ray(new Point(2,0,0), new Vector(-4, 1,2));
        //אם מוחזר null
        assertNull(triangle.findIntersections(ray), "ERROR: Wrong number of points intersects the sphere " +
                "when the point outside against edge the triangle");


        //TC03: the point outside against vertex
        ray = new Ray(new Point(2,0,0), new Vector(-2, -3,2));
        //אם מוחזר null
        assertNull(triangle.findIntersections(ray), "ERROR: Wrong number of points intersects the sphere " +
                "when the point outside against vertex the triangle");


        // =============== Boundary Values Tests ==================
        //TC11: the point on edge of the triangle
        ray = new Ray(new Point(2,0,0), new Vector(-2, 3,2));
        assertNull(triangle.findIntersections(ray),"ERROR: Wrong number of points intersects the sphere " +
                "when the point on edge of the triangle");

        //TC12: the point in vertex of the triangle
        ray = new Ray(new Point(2,0,0), new Vector(-5, 3,2));
        assertNull(triangle.findIntersections(ray),"ERROR: Wrong number of points intersects the sphere " +
                "when the point in vertex of the triangle");

        //TC13: the point on edge's continuation
        ray = new Ray(new Point(2,0,0), new Vector(1, -5,2));
        assertNull(triangle.findIntersections(ray),"ERROR: Wrong number of points intersects the sphere " +
                "when the point on edge's continuation");
    }
}