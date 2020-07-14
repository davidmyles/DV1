package ApplicationFiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class which creates an array containing pixel values from an input image
 */
public class InputImage {

    File inputFile;
    //public int w;
    //public int h;
    BufferedImage inputImage;
    double[][] inputImageArray;

    /**
     * Retrieves an image from file and creates a BufferedImage
     * "/Users/davidjmyles/IdeaProjects/DV1/trainingImages/img_01.png"
     */
    public InputImage(String filepath, int w, int h) throws IOException {
        inputFile = new File(filepath);
        inputImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        inputImage = ImageIO.read(inputFile);
    }

    /**
     * Takes BufferedImage as a parameter and creates double 2d array with values matching image pixels. Array is
     * returned
     */
    public double[][] createInputImageArray(BufferedImage bi) {
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
     * Accessor for BufferedImage object
     */
    public BufferedImage getInputImage() {
        return inputImage;
    }

    /**
     * Accessor for array object
     */
    public double [][] getInputImageArray() {
        return inputImageArray;
    }
}
