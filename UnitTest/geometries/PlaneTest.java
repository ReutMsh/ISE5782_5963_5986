package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
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
}