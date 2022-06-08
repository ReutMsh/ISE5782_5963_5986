package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;
import static primitives.Util.isZero;
import static primitives.Util.random;

/**
 * A department responsible for casting a beam of rays.
 * Each show contains information about the area to which we will cast the rays.
 * The number of rays he cast.
 *
 * Department activities:
 * Imposition of a beam of rays
 *
 * @author Reut and Odelya
 */
public class MultipleRay {

    private BlackBoard blackBoard;
    private double countRay;

    /**
     * constructor
     * (for Anti-Aliasing)
     * @param vX - axis x of the board.
     * @param vY - axis y of the board.
     * @param cPoint - center point.
     * @param rX - length the board.
     * @param rY - width the board.
     * @param countRay - count ray to construct.
     */
    public MultipleRay(Vector vX, Vector vY, Point cPoint, double rX, double rY, double countRay) {
        blackBoard = new BlackBoard(vX, vY, cPoint, rX, rY);
        this.countRay = countRay;
    }

    /**
     * constructor
     * (for soft Shadow)
     * @param blackBoard - the board to construct the rays.
     * @param countRay - count ray to construct.
     */
    public MultipleRay(BlackBoard blackBoard, double countRay){
        this.blackBoard = blackBoard;
        this.countRay = countRay;
    }

    /**
     * construct multi rays from start point to
     * random points in range nX*nY around center point.
     * @param startPoint - p0 of rays
     * @return list of construct rays.
     */
    public List<Ray> constructMultipleRay(Point startPoint){
        if(blackBoard == null)
            return new ArrayList<>();

        List<Ray> rayList = new ArrayList<>();

        for (int k = 0; k < countRay; k++) {
            double moveX = random(-blackBoard.rX / 2, blackBoard.rX / 2);
            double moveY = random(-blackBoard.rY / 2, blackBoard.rY / 2);
            Point movePoint = blackBoard.centerPoint;
            if (!isZero(moveX)) { movePoint = movePoint.add(blackBoard.vX.scale(moveX));}
            if (!isZero(moveY)) { movePoint = movePoint.add(blackBoard.vY.scale(moveY));}
            rayList.add(new Ray(startPoint, movePoint.subtract(startPoint)));
        }
        return rayList;
    }
}
