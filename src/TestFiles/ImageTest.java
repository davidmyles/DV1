package TestFiles;

import ApplicationFiles.Image;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Image objects
 */
class ImageTest {

    /**
     * Test image retrieved from file and pixel values used to initialise double array. Test verifies that array and
     * image dimensions match
     */
    @Test
    void createInputImageTest() throws IOException {
        Image i1 = new Image();
        BufferedImage bi1 = i1.inputImage("/Users/davidjmyles/IdeaProjects/DV1/testimages/img_01.png",9, 9);
        double [][] iia1 = i1.createInputImageArray(bi1);
        assertEquals (bi1.getWidth(), iia1.length);
        assertEquals (bi1.getHeight(), iia1[0].length);
    }

    /**
     * Test image retrieved from file and pixel values used to initialise double array. Test verifies that image pixel
     * value matches array element at 2 randomly selected points.
     */
    @Test
    void populateInputImageArrayTest() throws IOException {
        Image i1 = new Image();
        BufferedImage bi1 = i1.inputImage("/Users/davidjmyles/IdeaProjects/DV1/testimages/img_01.png",9, 9);
        double [][] d1 = i1.createInputImageArray(bi1);
        Random r2 = new Random();
        int i2 = r2.nextInt(d1.length);
        Color thisColor1 = new Color (i1.getInputImage().getRGB(i2, i2));
        int finalColor1 = (thisColor1.getRed() + thisColor1.getGreen() + thisColor1.getBlue())/3;
        int i3 = r2.nextInt(d1.length);
        Color thisColor2 = new Color (i1.getInputImage().getRGB(i3, i3));
        int finalColor2 = (thisColor2.getRed() + thisColor2.getGreen() + thisColor2.getBlue())/3;
        assertEquals(i1.getInputImage().getWidth(),d1.length);
        assertEquals(i1.getInputImage().getHeight(),d1[0].length);
        assertEquals(d1 [i2][i2], finalColor1);
        assertEquals(d1 [i3][i3], finalColor2);
    }

    /**
     * Retrieves image from file, and creates array. Then crops image (removing 1 'layer' of pixels from each side of
     * the square, and creates 2nd array from this. Test length of cropped array, and values at 2 corners of the crop.
     * @throws IOException
     */
    @Test
    void cropImage() throws IOException {
        Image i1 = new Image();
        BufferedImage bi1 = i1.inputImage("/Users/davidjmyles/IdeaProjects/DV1/testimages/img_01.png",9, 9);
        double [][] d1 = i1.createInputImageArray(bi1);
        BufferedImage bi2 = i1.cropImage(bi1,1,1,7,7);
        double [][] d2 = i1.createInputImageArray(bi2);
        assertEquals(d1.length-2, d2.length);
        assertEquals(d1[0].length-2, d2[0].length);
        assertEquals(d1[1][1], d2[0][0]);
        assertEquals(d1[7][7], d2[6][6]);
    }
}