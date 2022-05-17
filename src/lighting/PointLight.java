package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class PointLight
 * extends Light implements LightSource
 * @author Reut and odelya
 */
public class PointLight extends Light implements LightSource{

    private Point position;
    private double kC;
    private double kL;
    private double kQ;

    //region constructor

    /**
     * constructor (use super constructor)
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        /**position = PL -> spotLight location */
        this.position = position;
        this.kC = 1;
        this.kL = 0;
        this.kQ = 0;
    }

    //endregion

    //region setters

    /**
     * set kC
     * @param kC
     * @return PointLight
     */
    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * set kL
     * @param kL
     * @return PointLight
     */
    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * set kQ
     * @param kQ
     * @return PointLight
     */
    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    //endregion

    @Override
    public Color getIntensity(Point p) {
        return getIntensity().reduce( kC + kL*p.distance(position) + kQ*p.distanceSquared(position));
    }

    @Override
    public Vector getL(Point p) {
       return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
