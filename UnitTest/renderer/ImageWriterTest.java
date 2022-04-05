package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing ImageWriter Class
 *
 * @author Reut and Odelya
 *
 */
class ImageWriterTest {

    /**
     * Method that adds a grid to the image (rows and columns)
     * @param colorTheGrid
     * @param image
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