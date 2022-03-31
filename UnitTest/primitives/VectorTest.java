package primitives;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Vector;


import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Reut & Odelya
 */
class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    Vector u = v1.normalize();

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     * Test method for {@link primitives.Vector#Vector(Double3)}.
     */
    @Test
    void testConstructor(){

        // =============== Boundary Values Tests ==================

        //TC11: if zero vector does not throw an exception in 3 doubles constructor
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "ERROR: zero vector does not throw an exception");


        //TC12: if zero vector does not throw an exception in Double3 constructor
        assertThrows(IllegalArgumentException.class, () -> new Vector(new Double3(0,0,0)), "ERROR: zero vector does not throw an exception");

    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     */
    @Test
    void testAdd() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 the func add
        assertEquals(v1.add(v2), new Vector(-1, -2, -3), "ERROR: add Vector wrong");

        // =============== Boundary Values Tests ==================

        //TC11 try to add a counters Vectors
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)), "ERROR: No error was thrown when we added counter vectors that create Vector zero");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============

        //TC01 try the scale func
        assertEquals(v1.scale(4), new Vector(4, 8, 12), "ERROR: scale vector with scalar wrong");

        // =============== Boundary Values Tests ==================

        //TC11 try scale vector with zero
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "ERROR: scale vector with zero doesn't throw exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 test compute lengthSquared func
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 test compute length func
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 check if the scalar vector return true value
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");

        // =============== Boundary Values Tests ==================

        //TC11 check if the scalar vector return 0 when the vectors vertical.
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============

        Vector vr = v1.crossProduct(v3);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================

        // TC11: test zero vector from cross-product of co-lined vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), "crossProduct() for parallel vectors does not throw an exception");
}

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 check the vector normalization (u= vector that normalized before)
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");

        //TC02 try when the vectors are co-lined
        assertThrows(IllegalArgumentException.class, ()->v1.crossProduct(u),  "ERROR: the normalized vector is not parallel to the original one\"");

        //TC02 try when the vectors parallel
        assertTrue(v1.dotProduct(u) >= 0, "ERROR: the normalized vector is opposite to the original one");
    }
}