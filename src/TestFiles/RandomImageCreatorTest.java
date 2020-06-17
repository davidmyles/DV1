package TestFiles;

import ApplicationFiles.RandomImageCreator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for creation of initial array and subsequent image from RandomImageCreator class
 */
class RandomImageCreatorTest {

    /**
     * Creates array and tests that it has the correct dimensions
     */
    @Test
    void createArrayTest() {
        RandomImageCreator ric1 = new RandomImageCreator();
        ric1.createArray();
        for (int i = 0; i < ric1.h; i++) {
            for (int j = 0; j < ric1.w; j++) {
                assertEquals(ric1.h, ric1.getPixelArray()[i].length);
                assertEquals(ric1.w, ric1.getPixelArray()[j].length);
            }
        }
    }

    /**
     * Creates array, selects random element and tests that the value is the correct range for image pixel creation
     */
    @Test
    void createImageTest() {
        RandomImageCreator ric2 = new RandomImageCreator();
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
        RandomImageCreator ric3 = new RandomImageCreator();
        ric3.createArray();
        ric3.createImage();
        int i3a = ric3.getNewImage().getHeight();
        int i3b = ric3.getNewImage().getWidth();
        for (int i = 0; i < ric3.h; i++) {
            for (int j = 0; j < ric3.w; j++) {
                assertEquals(ric3.getPixelArray()[i].length, i3a);
                assertEquals(ric3.getPixelArray()[j].length, i3b);
            }
        }
    }

    /**
     * Creates array & image. Selects random position from array and tests if corresponding image pixel contains the
     * same value
     */
    @Test
    void imagePopulationTest() {
        RandomImageCreator ric4 = new RandomImageCreator();
        ric4.createArray();
        ric4.createImage();
        Random r4 = new Random();
        int i4 = r4.nextInt(ric4.h);
        Color thisColor = new Color (ric4.getNewImage().getRGB(i4, i4));
        int finalColor = thisColor.getRed() + thisColor.getGreen() + thisColor.getBlue();
        assertEquals(ric4.getPixelArray() [i4][i4], finalColor);
    }
}