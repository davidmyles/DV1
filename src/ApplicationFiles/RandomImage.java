package ApplicationFiles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class which creates an array of random integers and uses this to generate a test image
 */
public class RandomImage {

    public int width = 9;
    public int height = 9;
    double[][] pixelArray;
    Random random = new Random();
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    /**
     * Creates a 2D array of random integers between 0-256
     */
    public void createArray() {
        pixelArray = new double[width][height];
        for(int i = 0 ; i < pixelArray.length ; i++ ) {
            for ( int j = 0 ; j < pixelArray[i].length ; j++ ) {
                pixelArray[i][j] = random.nextInt(256);
            }
        }
    }

    /**
     * Creates image with pixel values from an array
     * @param pixelArray - array with values used to create image
     */
    public void createImage(double [][] pixelArray)	    {
        for(int y = 0; y < height; y++)  {
            for(int x = 0; x < width; x++)  {
                int r = (int) pixelArray[x][y];
                Color thisColor = new Color(r, r, r);
                int setColour = thisColor.getRGB();
                newImage.setRGB(x, y, setColour);
            }
        }
    }

    /**
     * Accessor for the generated image
     * @return newImage
     */
    public BufferedImage getNewImage()	{
        return newImage;
    }

    /**
     * Accessor for the generated array
     * @return pixelArray
     */
    public double[][] getPixelArray()	{
        return pixelArray;
    }


}

