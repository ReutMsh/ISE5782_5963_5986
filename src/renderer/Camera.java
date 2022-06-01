package renderer;
import org.ejml.data.SimpleMatrix;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.*;

/**
 * Class Camera
 *
 * @author Reut and Odelya
 */
public class Camera {

    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;

    private double width;
    private double height;
    private double distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

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
        //check if the vectors are vertical
        if(!isZero(vTo.dotProduct(vUp)))
        {
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

    //region set for image
    public Camera setImageWriter(ImageWriter imageWriter)
    {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }
    //endregion

    //region methods

    //region renderImage

    /**
     *Check the field values - that they are not empty
     * For each pixel we get the appropriate color by sending a ray
     */
    public void renderImage() {
        renderCheckExcepted();

        //A loop that colors each pixel in a color that suits it
        for (int i =0; i<imageWriter.getNy(); i++)
        {
            for (int j =0; j<imageWriter.getNx(); j++)
            {
               Ray rayPixel= constructRay(imageWriter.getNx() ,imageWriter.getNy() , j, i );
               Color colorPixel= rayTracer.traceRay(rayPixel);
               imageWriter.writePixel(j, i, colorPixel);
            }
        }


    }

    /**
     * for Anti-Aliasing
     * Check the field values - that they are not empty
     * For each pixel we get the appropriate color by sending a ray
     * check about countRay random rays
     * if the color different-> check countRay^2 random rays
     */
    public void renderImage(int countRay) {
        renderCheckExcepted();

        int nY = imageWriter.getNy();
        int nX = imageWriter.getNx();

        //A loop that colors each pixel in a color that suits it

        for (int i = 0; i< nY; i++) {
            for (int j = 0; j< nX; j++)
            {
                List<Ray> raysPixel= constructRay(nX, nY, j, i ,countRay);
                Color colorPixel= rayTracer.traceRay(raysPixel);

                if(!colorPixel.isClosesColor(rayTracer.traceRay(raysPixel.get(0)))) {
                    raysPixel= constructRay(nX, nY, j, i ,countRay*countRay);
                    colorPixel= rayTracer.traceRay(raysPixel);
                }


                imageWriter.writePixel(j, i, colorPixel);
            }
        }

    }

    /**
     * check if one or more values are not Initialized.
     */
    private void renderCheckExcepted() {
        if( width == 0 )
        {  throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" ,  "width");}
        if( height == 0)
        {   throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" ,"height");}
        if(distance == 0)
        {     throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" , "distance");}
        if(imageWriter == null)
        {   throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" , "imageWriter");}
        if(rayTracer == null)
        {  throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" , "rayTracerBase");}
    }

    //endregion

    //region constructRay
    /**
     * build ray from the camera to center pixel desired
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return Ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pCenterVP = p0.add(vTo.scale(distance));
        Point pIJ = getCenterPixel(pCenterVP, nX, nY, j, i);

        return new Ray(p0, pIJ.subtract(p0));
    }

    /**
     * for Anti-Aliasing
     * build list of rays from the camera to random point in pixel desired.
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @param countRay
     * @return
     */
    private List<Ray> constructRay(int nX, int nY, int j, int i, int countRay){
        List<Ray> rayList = new ArrayList<>();
        Point pCenterVP = p0.add(vTo.scale(distance));
        double rX = width/ nX;
        double rY = height/ nY;

        Point pCenterPixel = getCenterPixel(pCenterVP, nX, nY, j, i);
        rayList.add(new Ray(p0, pCenterPixel.subtract(p0)));

        MultipleRay multipleRay = new MultipleRay(vRight, vUp, pCenterPixel, rX, rY, countRay-1);
        rayList.addAll(multipleRay.constructMultipleRay(p0));

        return rayList;
    }

    /**
     * get center of specific pixel
     * @param pCenterVP
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return Point
     */
    private Point getCenterPixel(Point pCenterVP, int nX, int nY, int j, int i) {
        double rX = width/ nX;
        double rY = height/ nY;
        double xJ = (j - (nX -1)/2d)*rX;
        double yI = -(i - (nY -1)/2d)*rY;

        Point pCenterPixel = pCenterVP;
        if(!isZero(xJ)) { pCenterPixel = pCenterPixel.add(vRight.scale(xJ));}
        if(!isZero(yI)) {pCenterPixel = pCenterPixel.add(vUp.scale(yI));}
        return pCenterPixel;
    }

    //endregion

    /**
     * Creates a grid of lines on the image - Grid
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color) {
        //check if the value is not null
        if(imageWriter == null)
        {   throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" , "imageWriter");}

        //A loop that colors the rows
        for (int i =0; i< imageWriter.getNy(); i+=interval)
        {
            for (int j =0; j<imageWriter.getNx(); j++)
            {
                imageWriter.writePixel(j, i, color);
            }
        }

        //A loop that colors the columns
        for (int j =0; j<imageWriter.getNx(); j+=interval)
        {
            for (int i =0; i<imageWriter.getNy(); i++)
            {
                imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Act of delegation
     */
    public void writeToImage() {
        //check if the value is not null
        if(imageWriter == null)
        {   throw new MissingResourceException("ERROR: one or more values are not Initialized","Camera" , "imageWriter");}

        imageWriter.writeToImage();
    }

    //region move camera

    //region move around vTo
    public Camera moveAroundVTo(double angle){
        vRight = moveCameraAroundVector(vTo, vRight, vUp, angle);
        vUp = vRight.crossProduct(vTo).normalize();
        return this;
    }

//endregion

    //region move around vUp
    public Camera moveAroundVUp(double angle){
        vTo = moveCameraAroundVector(vUp, vTo, vRight, angle);
        vRight = vTo.crossProduct(vUp).normalize();
        return this;
    }
//endregion

    //region move around vRight
    public Camera moveAroundVRight(double angle){
        vUp = moveCameraAroundVector(vRight, vUp, vTo, angle);
        vTo = vUp.crossProduct(vRight).normalize();
        return this;
    }
//endregion

    private Vector moveCameraAroundVector(Vector rotationVector, Vector vectorToMove, Vector orthogonalVector, double angle) {
        SimpleMatrix matrixP = new SimpleMatrix(3,3);
        matrixP.set(0,0, rotationVector.getX());
        matrixP.set(1,0, rotationVector.getY());
        matrixP.set(2,0, rotationVector.getZ());
        matrixP.set(0,1, vectorToMove.getX());
        matrixP.set(1,1, vectorToMove.getY());
        matrixP.set(2,1, vectorToMove.getZ());
        matrixP.set(0,2, orthogonalVector.getX());
        matrixP.set(1,2, orthogonalVector.getY());
        matrixP.set(2,2, orthogonalVector.getZ());

        SimpleMatrix matrixA = new SimpleMatrix(3,3);
        matrixA.set(0,0, 1);
        matrixA.set(1,0, 0);
        matrixA.set(2,0, 0);
        matrixA.set(0,1, 0);
        matrixA.set(1,1, alignZero(Math.cos(angle)));
        matrixA.set(2,1, alignZero(Math.sin(angle)));
        matrixA.set(0,2, 0);
        matrixA.set(1,2, -alignZero(Math.sin(angle)));
        matrixA.set(2,2, alignZero(Math.cos(angle)));

        SimpleMatrix matrixInvertP = matrixP.invert();

        SimpleMatrix copyMatrix = matrixP.mult(matrixA).mult(matrixInvertP);

        SimpleMatrix matrixVectorToMove = new SimpleMatrix(3,1);

        matrixVectorToMove.set(0,0,vectorToMove.getX());
        matrixVectorToMove.set(1,0,vectorToMove.getY());
        matrixVectorToMove.set(2,0,vectorToMove.getZ());

        matrixVectorToMove = copyMatrix.mult(matrixVectorToMove);

        return new Vector(matrixVectorToMove.get(0,0), matrixVectorToMove.get(1,0), matrixVectorToMove.get(2,0)).normalize();
    }
    //endregion
    //endregion

}
