package primitives;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.RayTest class
 * @author Reut & Odelya
 */
class RayTest {

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void testFindClosestPoint() {

        // ============ Equivalence Partitions Tests ==============

        //TC01 find point in the middle of the list is closest to the beginning of the ray
        Ray ray= new Ray(new Point(0,-2,0) , new Vector(-5 , 6,0));
        List<Point> pointList=new LinkedList<>();
        pointList.add(new Point(-4, 2.81,0));//F
        pointList.add(new Point(-5 , 4 ,0));//B
        pointList.add(new Point(-2 , 0.4 , 0));//C
        pointList.add(new Point(-6.01 , 5.21 , 0));//D
        pointList.add(new Point(-7.08 , 6.5 , 0));//E
        assertEquals(new Point(-2 , 0.4 , 0) , ray.findClosestPoint(pointList) , "ERROR: find point in the middle of the list TC01");

        // =============== Boundary Values Tests ==================

        //TC11 the list geometries is empty
        pointList = null;
        assertNull(ray.findClosestPoint(pointList) ,"ERROR: the list is empty TC11" );

        //TC12 The last point is closest to the beginning of the foundation
        pointList = new LinkedList<>();
        pointList.add(new Point(-2 , 0.4 , 0));//C
        pointList.add(new Point(-4, 2.81,0));//F
        pointList.add(new Point(-5 , 4 ,0));//B
        pointList.add(new Point(-6.01 , 5.21 , 0));//D
        pointList.add(new Point(-7.08 , 6.5 , 0));//E
        assertEquals(new Point(-2 , 0.4 , 0) , ray.findClosestPoint(pointList) , "ERROR: find point in the last of the list TC12");

        //TC13 The first point is closest to the beginning of the foundation
        pointList.remove(new Point(-2 , 0.4 , 0));//C
        pointList.add(new Point(-2 , 0.4 , 0));//C
        assertEquals(new Point(-2 , 0.4 , 0) , ray.findClosestPoint(pointList) , "ERROR: find point in the first of the list TC13");



    }
}