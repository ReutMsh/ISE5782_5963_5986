package renderer;


import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Camera
 *
 * @author Reut and odelya
 */
public class Camera {
    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;

    private double width;
    private double height;
    private double distance;

    //region get
    public Point getP0() {
        return p0;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }
    //endregion

    //region constructor

    /**
     * constructor
     * If the vector are not vertical- throw exception
     * Build vRight and normalize all the vectors
     * @param p0
     * @param vTo
     * @param vUp
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        this.p0 = p0;
        if(vTo.dotProduct(vUp)!= 0) {
            throw new IllegalArgumentException("the 3 point can't build a plan");
        }
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp).normalize();
    }
    //endregion

    //region set for view plane
    public Camera setVPSize(double width, double height)
    {
        this.height=height;
        this.width=width;
        return this;
    }

    public Camera setVPDistance(double distance)
    {
        this.distance=distance;
        return this;
    }
    //endregion

    /**
     * Produces a beam that passes through the view plane and the camera
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public Ray constructRay(int nX, int nY, int j, int i)
    {
        return null;
    }
}
