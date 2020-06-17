package ApplicationFiles;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class which creates an array of random integers and uses this to generate a test image
 */
public class RandomImageCreator {

    public int w = 5;
    public int h = 5;
    double[][] pixelArray;
    Random random = new Random();
    BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


    /**
     * Creates a 2D array of random integers between 0-256
     */
    public void createArray() {
        pixelArray = new double[w][h];
        for(int i = 0 ; i < pixelArray.length ; i++ ) {
            for ( int j = 0 ; j < pixelArray[i].length ; j++ ) {
                pixelArray[i][j] = random.nextInt(256);
            }
        }
    }

    /**
     * Uses the array to generate an image where the pixel values (in greyscale) correspond to the array
     * element at that position
     */
    public void createImage()	{
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int r = (int) pixelArray[x][y];
                newImage.setRGB(x, y, r);
            }
        }
    }

    /**
     * Accessor for the generated image
     */
    public BufferedImage getNewImage()	{
        return newImage;
    }

    /**
     * Accessor for the generated array
     */
    public double[][] getPixelArray()	{
        return pixelArray;
    }
}

