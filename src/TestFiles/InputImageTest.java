package TestFiles;

import ApplicationFiles.InputImage;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for creation of array in InputImage class
 */
class InputImageTest {

    /**
     * Test image retrieved from file and pixel values used to initialise double array. Test verifies that array and
     * image dimensions match
     */
    @Test
    void createInputImageArrayTest() throws IOException {
        InputImage ii1 = new InputImage("/Users/davidjmyles/IdeaProjects/DV1/junitImages/img_01.png",5, 5);
        BufferedImage bi1 = ii1.getInputImage();
        double [][] iia1 = ii1.createInputImageArray(bi1);
        //double [][] iia1 = ii1.getInputImageArray();
        assertEquals (bi1.getWidth(), iia1.length);
        assertEquals (bi1.getHeight(), iia1[0].length);
    }

    /**
     * Test image retrieved from file and pixel values used to initialise double array. Test verifies that image pixel
     * value matches array element at randomly selected point.
     */
    @Test
    void populateInputImageArrayTest() throws IOException {
        InputImage ii2 = new InputImage("/Users/davidjmyles/IdeaProjects/DV1/junitImages/img_01.png",5, 5);
        BufferedImage bi2 = ii2.getInputImage();
        ii2.createInputImageArray(bi2);
        double [][] iia2 = ii2.createInputImageArray(bi2);
        //double [][] iia2 = ii2.getInputImageArray();
        Random r2 = new Random();
        int i2 = r2.nextInt(iia2.length);
        Color thisColor = new Color (ii2.getInputImage().getRGB(i2, i2));
        int finalColor = (thisColor.getRed() + thisColor.getGreen() + thisColor.getBlue())/3;
        assertEquals(iia2 [i2][i2], finalColor);
    }
}