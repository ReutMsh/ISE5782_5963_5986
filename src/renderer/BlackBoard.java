package renderer;

import primitives.Point;
import primitives.Vector;

/**
 *A department responsible for the area to which we cast a beam of rays.
 * Each show contains information about the area:
 * The vectors that make up the surface
 * The size of the surface
 * center the surface.
 * @author Reut and Odelya
 */
public class BlackBoard {
    public double rX;
    public double rY;
    public Vector vX;
    public Vector vY;
    public Point centerPoint;

    /**
     * constructor for soft shadow
     * @param alfa
     * @param distance
     * @param cPoint
     */
    public BlackBoard(double alfa, double distance, Vector directionRay, Point cPoint) {
        centerPoint = cPoint;
        vX = directionRay.minD();
        vY = directionRay.crossProduct(vX);
        rX = distance*Math.tan(alfa);
        rY = rX;
    }

    /**
     * constructor for Anti-Aliasing
     * @param vX
     * @param vY
     * @param cPoint
     * @param rX
     * @param rY
     */
    public BlackBoard(Vector vX, Vector vY, Point cPoint, double rX, double rY) {
        centerPoint = cPoint;
        this.vX = vX;
        this.vY = vY;
        this.rX = rX;
        this.rY = rY;
    }
}
