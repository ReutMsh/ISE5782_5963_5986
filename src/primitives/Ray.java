package primitives;

import java.util.List;
import java.util.Objects;

/**
 * Class Ray define ray
 * with normalise vector and point
 * @author Reut and odelya
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    //region constructor

    /**
     * constructor
     * normalize the vector and build the ray
     *
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    //endregion

    //region get

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     *Finding a point that is at a distance of 't' from the beginning of the beam
     * @param t
     * @return
     */
    public Point getPoint(double t) {
        return getP0().add(getDir().scale(t));
    }

    /**
     * Receives a list of points and returns the point closest to the beginning of the fund
     * @param pointList
     * @return
     */
    public Point findClosestPoint(List<Point> pointList)
    {
        return null;
    }

}
