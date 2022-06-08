package primitives;

/**
 * Material classes
 * Responsible for entering the data of the coefficients of light
 * PDS
 * @author Reut and Odelya
  */
public class Material {

    /**
     * Promotes transparency
     */
    public Double3 kT = Double3.ZERO;
    /**
     * Coefficient of reflection
     */
    public Double3 kR = Double3.ZERO;
    /**
     * diffusion constant.
     */
    public Double3 kD = Double3.ZERO;
    /**
     * reflection constant.
     */
    public Double3 kS = Double3.ZERO;
    /**
     * the luster according v*r.
     */
    public int nShininess = 0;

    //region setters

    /**
     * builder set kD with Double3
     * @param kD - diffusion constant.
     * @return this (builder)
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * builder set kD with double
     * @param doubleTokD - diffusion constant.
     * @return this(builder)
     */
    public Material setKD(double doubleTokD) {
        this.kD = new Double3(doubleTokD);
        return this;
    }

    /**
     * builder set kS
     * @param kS - reflection constant.
     * @return this (builder)
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * builder set kS with double
     * @param doubleTokS - reflection constant.
     * @return this (builder)
     */
    public Material setKS(double doubleTokS) {
        this.kS = new Double3(doubleTokS);
        return this;
    }


    /**
     * builder set nShininess
     * @param nShininess - the luster according v*r.
     * @return this (builder)
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * builder set kT
     * @param kT - transparency promotes.
     * @return this (builder)
     */
    public Material setKT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * builder set kR
     * @param kR - reflection promotes.
     * @return this (builder)
     */
    public Material setKR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    //endregion


}
