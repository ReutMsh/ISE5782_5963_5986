package primitives;

import java.util.Objects;

/**
 * Ray class
 * arg: Point p0
 * arg: Vector dir
 */
public class Ray {
    private Point p0;
    private Vector dir;

    /***
     * constructor
     * normalize the vector and build the ray
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

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
