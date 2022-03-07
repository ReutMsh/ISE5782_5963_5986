package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for geometries.Cylinder class
 * @author Reut & Odelya
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cylinderToTest = new Cylinder(new Ray(new Point(0,0,3), new Vector(0,0,10)),4, 10);
        // ============ Equivalence Partitions Tests ==============
        //TC01 when the point on the side of the cylinder
        assertEquals(new Vector(4, 0, 0).normalize(), cylinderToTest.getNormal(new Point(4, 0, 5)), "Bad normal to Cylinder");

        //TC02 when the point on the bottom base of the cylinder
        assertEquals(new Vector(0,0,1), cylinderToTest.getNormal(new Point(2, 0, 3)), "Bad normal to Cylinder");

        //TC01 when the point on the top base of the cylinder
        assertEquals(new Vector(0,0,-1), cylinderToTest.getNormal(new Point(3, 0, 13)), "Bad normal to Cylinder");

        // =============== Boundary Values Tests ==================
        //TC11 when the point is the center of the bottom base of the cylinder
        assertEquals(new Vector(0,0,1), cylinderToTest.getNormal(new Point(0, 0, 3)), "Bad normal to Cylinder");


        //TC12 when the point is the center of the top base of the cylinder
        assertEquals(new Vector(0,0,-1), cylinderToTest.getNormal(new Point(0, 0, 13)), "Bad normal to Cylinder");

    }
}