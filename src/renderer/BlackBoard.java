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
     * @param alfa - size of the opening angle for getBlackBoard.
     * @param distance - distance of the board from p0;
     * @param cPoint - center of board.
     */
    public BlackBoard(double alfa, double distance, Vector directionRay, Point cPoint) {
        centerPoint = cPoint;
        vX = directionRay.verticalVector();
        vY = directionRay.crossProduct(vX);
        rX = distance*Math.tan(alfa);
        rY = rX; //rx==ry
    }

    /**
     * constructor for Anti-Aliasing
     * @param vX - axis x of the board.
     * @param vY - axis y of the board.
     * @param cPoint - center point.
     * @param rX - length the board.
     * @param rY - width the board.
     */
    public BlackBoard(Vector vX, Vector vY, Point cPoint, double rX, double rY) {
        centerPoint = cPoint;
        this.vX = vX;
        this.vY = vY;
        this.rX = rX;
        this.rY = rY;
    }
}
