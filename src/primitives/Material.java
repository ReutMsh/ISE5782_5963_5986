package primitives;

/**
 * Material classes
 * PDS
 * @author Reut and odelya
  */
public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;

    //region setters

    /**
     * set kD with Double3
     * @param kD
     * @return Material
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set kD with double
     * @param doubleTokD
     * @return Material
     */
    public Material setKD(double doubleTokD) {
        this.kD = new Double3(doubleTokD);
        return this;
    }

    /**
     * set kS
     * @param kS
     * @return Material
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set kS with double
     * @param doubleTokS
     * @return Material
     */
    public Material setKS(double doubleTokS) {
        this.kS = new Double3(doubleTokS);
        return this;
    }


    /**
     * set nShininess
     * @param nShininess
     * @return Material
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    //endregion

    //region
}
