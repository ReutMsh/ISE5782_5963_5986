package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 * @author Reut & Odelya
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sp = new Sphere(new Point(2, 0, 0),2 );
        assertEquals(new Point(1, -1.732, 0).subtract(new Point(2, 0, 0)).normalize(), sp.getNormal(new Point(1, -1.732, 0)), "Bad normal to Sphere");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere( new Point(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals( 2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Ray ray = new Ray(new Point(0.5, 0,0), new Vector(1, 0,0));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(sphere.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts inside the sphere");
        assertEquals(sphere.findIntersections(ray).get(0), new Point(2,0,0), "ERROR: Wrong value point intersects the sphere " +
                "when the ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        ray = new Ray(new Point(3, 3,0), new Vector(1, 1,0));
        assertNull(sphere.findIntersections(ray) , "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        ray = new Ray(new Point(1, 0,1), new Vector(1, 0,-1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(sphere.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts at sphere and goes inside");
        assertEquals(sphere.findIntersections(ray).get(0), new Point(2,0,0), "ERROR: Wrong value point intersects the sphere " +
                "when the ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(1, 0,1), new Vector(1, 0,1));
        assertNull(sphere.findIntersections(ray), "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts at sphere and goes outside");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        ray = new Ray(new Point(1, 0,-2), new Vector(0, 0,1));
        p1 = new Point(1,0,-1);
        p2 = new Point(1,0,1);
        result = sphere.findIntersections(ray);
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals( 2, result.size(), "ERROR: Wrong number of points intersects the sphere " +
                                "when the ray starts before the sphere");
        if (result.get(0).getZ() > result.get(1).getZ())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "ERROR: Wrong value point intersects the sphere "+
                               "when the ray starts before the sphere ");

        // TC14: Ray starts at sphere and goes inside (1 points)
        ray = new Ray(new Point(1, 0,1), new Vector(0, 0,-1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(sphere.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts at sphere and goes inside");
        assertEquals(sphere.findIntersections(ray).get(0), new Point(1,0,-1), "ERROR: Wrong value point intersects the sphere " +
                "when the ray starts at sphere and goes inside");

        // TC15: Ray starts inside (1 points)
        ray = new Ray(new Point(1, 0,-0.5), new Vector(0, 0,-1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(sphere.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts inside");
        assertEquals(sphere.findIntersections(ray).get(0), new Point(1,0,-1), "ERROR: Wrong value point intersects the sphere " +
                "when the ray starts inside");
        // TC16: Ray starts at the center (1 points)
        ray = new Ray(sphere.getCenter(), new Vector(0, 0,-1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(sphere.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts at the center");
        assertEquals(sphere.findIntersections(ray).get(0), new Point(1,0,-1), "ERROR: Wrong value point intersects the sphere " +
                "when the ray starts at the center");
        // TC17: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(2,0,0), new Vector(1, 0,0));
        assertNull(sphere.findIntersections(ray) , "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        ray = new Ray(new Point(10,0,0), new Vector(1, 0,0));
        assertNull(sphere.findIntersections(ray) , "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        ray = new Ray(new Point(0,0,1), new Vector(1, 0,0));
        assertNull(sphere.findIntersections(ray) , "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        ray = new Ray(new Point(-1,0,1), new Vector(1, 0,0));
        assertNull(sphere.findIntersections(ray) , "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        ray = new Ray(new Point(2,0,-1), new Vector(1, 0,0));
        assertNull(sphere.findIntersections(ray) , "ERROR: Wrong number of points intersects the sphere " +
                "when the ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        ray = new Ray(new Point(10,0,0), new Vector(0, 0,1));
        assertNull(sphere.findIntersections(ray) , "ERROR: Wrong number of points intersects the sphere " +
                "when the Ray's line is outside, ray is orthogonal to ray start to sphere's center line");
    }


}