package geometries;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.PolygonTest class
 * @author Reut & Odelya
 */
class PolygonTest {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to triangle");
    }

    /**
     * Test method for {@link geometries.Polygon#findIntersections(Ray)}}.
     */
    @Test
    void testFindIntersections() {
        //polygon with vertices: a,b,c,d
        Polygon polygon = new Polygon(new Point(-20,0,0), new Point(0,0,50), new Point(40,0,0), new Point(0,0,-50));

        Ray ray = new Ray(new Point(0,-20,0), new Vector(10, 20, 0));
        // ============ Equivalence Partitions Tests ==============
        //TC01: the point inside the polygon
        assertEquals(polygon.findIntersections(ray).size(), 1, "ERROR: Wrong number of points intersects the polygon " +
                "when the point Inside the polygon");

        assertEquals(polygon.findIntersections(ray).get(0), new Point(10,0,0), "ERROR: Wrong number of points intersects the polygon " +
                "when the point Inside the polygon");

        //TC02: the point outside against edge
        ray = new Ray(new Point(0,-20,0), new Vector(60,20,20));
        assertNull(polygon.findIntersections(ray), "ERROR: Wrong number of points intersects the polygon " +
                "when the point outside against edge the polygon");


        //TC03: the point outside against vertex
        ray = new Ray(new Point(0,-20,0), new Vector(50,20,0));
        assertNull(polygon.findIntersections(ray), "ERROR: Wrong number of points intersects the polygon " +
                "when the point outside against vertex the polygon");


        // =============== Boundary Values Tests ==================
        //TC11: the point on edge of the polygon
        ray =new Ray(new Point(0,-20,0), new Vector(15,20,31.25));
        assertNull(polygon.findIntersections(ray),"ERROR: Wrong number of points intersects the polygon " +
                "when the point on edge of the polygon");

        //TC12: the point in vertex of the polygon
        ray = new Ray(new Point(0,-20,0), new Vector(0,20,50));
        assertNull(polygon.findIntersections(ray),"ERROR: Wrong number of points intersects the polygon " +
                "when the point in vertex of the polygon");

        //TC13: the point on edge's continuation
        ray = new Ray(new Point(0,-20,0), new Vector(9,20,72.5));
        assertNull(polygon.findIntersections(ray),"ERROR: Wrong number of points intersects the polygon " +
                "when the point on edge's continuation");

    }
}