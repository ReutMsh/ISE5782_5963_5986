package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

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
}