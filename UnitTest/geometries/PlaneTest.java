package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 * @author Reut & Odelya
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================
        //TC11 check if there is same point
        assertThrows(IllegalArgumentException.class, ()-> new Plane(new Point(1,1,1), new Point(1,2,1), new Point(1,1,1)), "ERROR: fail if try build plan with 2 same point");


        //TC12 if the tree point on the same straight
        assertThrows(IllegalArgumentException.class, ()-> new Plane(new Point(1,1,1), new Point(2,2,2), new Point(3,3,3)), "ERROR: fail build plan if the tree point on the same straight");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }


    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void testFindIntsersections() {

        Plane pl = new Plane(new Point(0,0,2), new Point(1,0,2), new Point(0,1,2));

        // ============ Equivalence Partitions Tests ==============
        // ray intersects the plane
        Ray ray = new Ray(new Point(-1,0,-1), new Vector(1, 0,1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(pl.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the plane");
        assertEquals(pl.findIntersections(ray).get(0), new Point(2,0,2), "ERROR: Wrong value point intersects the plane");

        //Ray does not intersect the plane
        ray = new Ray(new Point(3,0,3), new Vector(1, 0,1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane");

        // =============== Boundary Values Tests ==================
        //region Ray is parallel to the plane
        //the ray included in the plane
        ray = new Ray(new Point(3,0,2), new Vector(1, 0,1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane" +
                " when Ray is parallel to the plane and included in the plane");

        //the ray not included in the plane
        ray = new Ray(new Point(3,0,3), new Vector(1, 1,0));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane " +
                "when Ray is parallel to the plane and not included in the plane");
//endregion

        //region Ray is orthogonal to the plane
        //before the plane
        ray = new Ray(new Point(2,3,1), new Vector(0, 0,1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(pl.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the plane" +
                "when the ray is orthogonal to the plane before the plane");
        assertEquals(pl.findIntersections(ray).get(0), new Point(2,3,2), "ERROR: Wrong value point intersects the plane" +
                "when the ray is orthogonal to the plane before the plane");

        //in the plane
        ray = new Ray(new Point(5,3,2), new Vector(0, 0,1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(pl.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the plane" +
                "when the ray is orthogonal to the plane in the plane");
        assertEquals(pl.findIntersections(ray).get(0), new Point(5,3,2), "ERROR: Wrong value point intersects the plane" +
                "when the ray is orthogonal to the plane in the plane");

        //after the plane
        ray = new Ray(new Point(0,3,4), new Vector(0, 0,1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane" +
                "when the ray is orthogonal to the plane after the plane");

        //endregion

        //Ray is neither orthogonal nor parallel to and begins at the plane
        ray = new Ray(new Point(1,0,2), new Vector(1, 1,1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(pl.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the plane " +
                "when the ray is neither orthogonal nor parallel to and begins at the plane");
        assertEquals(pl.findIntersections(ray).get(0), new Point(2,2,2), "ERROR: Wrong value point intersects the plane " +
                "when the ray is neither orthogonal nor parallel to and begins at the plane");

        //Ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane
        ray = new Ray(pl.getQ0(), new Vector(0, 1,1));
        ///לבדוק מה קורה אם מוחזר null בטעות
        assertEquals(pl.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the plane " +
                "when the ray is neither orthogonal nor parallel to and begins in the same point which appears as reference point in the plane");
        assertEquals(pl.findIntersections(ray).get(0), new Point(0,2,2), "ERROR: Wrong value point intersects the plane " +
                "when the ray is neither orthogonal nor parallel to and the same point which appears as reference point in the plane");

    }
}