package primitives;

import java.util.Objects;

/**
 * Class Ray define ray
 * with normalise vector and point
 * @author Reut and odelya
 */
public class Ray {
    final private Point p0;
    final private Vector dir;

    //region constructor
    /**
     * constructor
     * normalize the vector and build the ray
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
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
}
