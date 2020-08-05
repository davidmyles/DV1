package ApplicationFiles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Class which creates an array of random integers and uses this to generate a test image
 */
public class RandomImage {

    public int w = 50;
    public int h = 50;
    double[][] pixelArray;
    double [] flatArray;
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

    public void flattenArray(double [][] d1)  {
        flatArray = Stream.of(d1).flatMapToDouble(DoubleStream::of).toArray();
    }

    /**
     * Uses the array to generate an image where the pixel values (in greyscale) correspond to the array
     * element at that position
     */
    public void createImage(double [][] pixelArray)	{
        for(int y = 0; y < h; y++)  {
            for(int x = 0; x < w; x++){
                int r = (int) pixelArray[x][y];
                Color thisColor = new Color(r, r, r);
                int setColour = thisColor.getRGB();
                newImage.setRGB(x, y, setColour);
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


    public double[] getFlatArray()  { return flatArray; }
}

