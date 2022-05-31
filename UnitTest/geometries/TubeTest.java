package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 * @author Reut & Odelya
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube tu = new Tube(new Ray(new Point(0,0,0), new Vector(0,0,1)), 4);
        assertEquals(new Vector(4, 0, 0).normalize(), tu.getNormal(new Point(4, 0, 5)), "Bad normal to Tube");

        // =============== Boundary Values Tests ==================
        // TC11: Connecting the point to the head of the horn of the Tube axis produces a right angle with the axis
        assertEquals(new Vector(1,0,0), tu.getNormal(new Point(5, 0, 2)), "Bad normal to Tube");
    }
}