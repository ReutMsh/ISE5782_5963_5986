package primitives;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Reut & Odelya
 */
class PointTest {
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(2, 0, 4);
    Point p3 = new Point(2, 0, 1);

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {

        // ============ Equivalence Partitions Tests ==============
        //TC01 check subtract func
        assertEquals(new Vector(1, 1, 1), (new Point(2, 3, 4).subtract(p1)), "ERROR: Point - Point does not work correctly");

        // =============== Boundary Values Tests ==================
        //TC11 check when subtract same point
        assertThrows(IllegalArgumentException.class, ()->p1.subtract(p1), "ERROR: subtract same vector doesn't throw exception");

    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void testAdd() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 check add func
        assertEquals(p1.add(new Vector(-1, -2, -3)), (new Point(0, 0, 0)), "ERROR: Point + Vector does not work correctly");

    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 check distanceSquared func
        assertEquals(p1.distanceSquared(p2), 6, "ERROR: distanceSquared wrong");

        // =============== Boundary Values Tests ==================

        //TC11 distanceSquared between point to herself
        assertEquals(p1.distanceSquared(p1), 0, "ERROR: distanceSquared between point to herself wrong");


    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 distanceSquared between point to herself distance
        assertEquals(p1.distance(p3), 3, "ERROR: distance wrong");

        // =============== Boundary Values Tests ==================

        //TC11 distance between point to herself
        assertEquals(p1.distance(p1), 0, "ERROR: distance wrong");


    }
}