package ApplicationFiles;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class which creates Image objects
 */
public class Image {

    private static File inputFile;
    private static BufferedImage inputImage;
    double[][] inputImageArray;

    /**
     * Creates a file to receive an image. Turns into BufferedImage of specified dimensions
     * @param filepath - image file storage location
     * @param w - width of image
     * @param h - height of image
     * @return BufferedImage created from input file
     * @throws IOException
     */
    public static BufferedImage inputImage(String filepath, int w, int h) throws IOException {
        inputFile = new File(filepath);
        inputImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        inputImage = ImageIO.read(inputFile);
        return inputImage;
    }

    /**
     * Takes BufferedImage as a parameter and creates double 2d array with values matching image pixels. Array is
     * returned
     */
    public static double[][] createInputImageArray(BufferedImage bi) {
        double[][] inputImageArray = new double[bi.getWidth()][bi.getHeight()];
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color thisColor = new Color(bi.getRGB(i, j));
                int finalColor = (thisColor.getRed() + thisColor.getGreen() + thisColor.getBlue())/3;
                inputImageArray[i][j] = finalColor;
            }
        }
        return inputImageArray;
    }

    /**
     * Takes image and crops to specified size
     * @param input - image to be cropped
     * @param x - x coordinate for crop position
     * @param y - y coordinate for crop position
     * @param w - width of cropped image
     * @param h - height of cropped image
     * @return cropped BufferedImage
     */
    public static BufferedImage cropImage(BufferedImage input, int x, int y, int w, int h)    {
        BufferedImage output = Scalr.crop(input,x,y,w,h);
        return output;
    }

    /**
     * Takes image and scales to specified dimensions
     * @param input - image to be scaled
     * @param width - pixel width of scaled image
     * @param height - pixel height of scaled image
     * @return scaled BufferedImage
     *//*
    public static BufferedImage scaleImage(BufferedImage input, int width, int height) {
        BufferedImage output = Scalr.resize(input, width,height);
        return output;
    }
*/
    /**
     * Accessor for BufferedImage object
     */
    public BufferedImage getInputImage() {
        return inputImage;
    }

}
