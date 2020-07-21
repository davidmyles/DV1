package TestFiles;

import ApplicationFiles.RandomImage;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for creation of initial array and subsequent image from RandomImageCreator class
 */
class RandomImageTest {

    /**
     * Creates array and tests that it has the correct dimensions
     */
    @Test
    void createArrayTest() {
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        assertEquals(ric1.h, ric1.getPixelArray().length);
        assertEquals(ric1.w, ric1.getPixelArray()[0].length);
    }

    /**
     * Creates array, selects random element and tests that the value is the correct range for image pixel creation
     */
    @Test
    void createImageTest() {
        RandomImage ric2 = new RandomImage();
        ric2.createArray();
        Random r2a = new Random();
        Random r2b = new Random();
        int i2a = r2a.nextInt(ric2.h);
        int i2b = r2b.nextInt(ric2.w);
        assertTrue(ric2.getPixelArray() [i2a][i2b] < 257);
    }

    /**
     * Creates array & image. Tests that image is of correct dimension as determined by the array
     */
    @Test
    void imageDimensionTest() {
        RandomImage ric3 = new RandomImage();
        ric3.createArray();
        double [][] d2 = ric3.getPixelArray();
        ric3.createImage(d2);
        int i3a = ric3.getNewImage().getWidth();
        int i3b = ric3.getNewImage().getHeight();
        assertEquals(ric3.getPixelArray().length, i3a);
        assertEquals(ric3.getPixelArray()[0].length, i3b);
    }

    /**
     * Creates array & image. Selects random position from array and tests if corresponding image pixel contains the
     * same value
     */
    @Test
    void imagePopulationTest() {
        RandomImage ric4 = new RandomImage();
        ric4.createArray();
        double [][] d1 = ric4.getPixelArray();
        ric4.createImage(d1);
        Random r4 = new Random();
        int i4 = r4.nextInt(d1.length);
        Color thisColor = new Color (ric4.getNewImage().getRGB(i4, i4));
        int finalColor = (thisColor.getRed() + thisColor.getGreen() + thisColor.getBlue())/3;
        assertEquals(d1 [i4][i4], finalColor);
    }
}