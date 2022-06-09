package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;


/**
 *Unit tests for renderer.ImageWriter class
 * @author Reut and Odelya
 *
 */
class ImageWriterTest {

    //region method
    /**
     * Method that adds a grid to the image (rows and columns)
     * @param colorTheGrid the color that which him we will paint the image
     * @param image return the image with a grid in color
     */
    void createGrid(Color colorTheGrid , ImageWriter image)
    {
        //A loop that colors the rows
        for (int i =0; i<image.getNy(); i+=50)
        {
            for (int j =0; j<image.getNx(); j++)
            {
                image.writePixel(j, i, colorTheGrid);
            }
        }

        //A loop that colors the columns
        for (int j =0; j<image.getNx(); j+=50)
        {
            for (int i =0; i<image.getNy(); i++)
            {
                image.writePixel(j, i, colorTheGrid);
            }
        }
    }
    //endregion

    /**
     * A test that tests simple image construction (without geometric shapes)
     * {@link ImageWriter#writePixel(int, int, Color)}
     * {@link ImageWriter#writeToImage()}
     */
    @Test
    void testWriteImage()
    {
        ImageWriter imageWriter = new ImageWriter("gridImage" ,800, 500);

        //A loop that colors the pixels in color
        for (int i =0; i<imageWriter.getNy(); i++)
        {
            for (int j =0; j<imageWriter.getNx(); j++)
            {
                imageWriter.writePixel(j, i,  new Color(0 , 168 , 243));
            }
        }

        createGrid(new Color(244 , 135 , 41) , imageWriter );
        imageWriter.writeToImage();
    }
}