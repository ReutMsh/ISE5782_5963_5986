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

    /**
     * set kT
     * @param kT
     * @return Material
     */
    public Material setKT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * set kR
     * @param kR
     * @return Material
     */
    public Material setKR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    //endregion


}
