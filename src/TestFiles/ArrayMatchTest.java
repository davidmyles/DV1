package TestFiles;

import ApplicationFiles.InputImage;
import ApplicationFiles.RandomImageCreator;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArrayMatchTest {

    /**
     * Test for array/image processing. Initialises random double array, which is used to create image. Image is saved
     * to file. File is then retrieved and pixel values used to initialise a 2nd array. Tests check that both arrays
     * are the same length, and that the values at 4 randomly generated points are matching.
     */
    @Test
    void arrayMatchTest() throws IOException {
        RandomImageCreator ric = new RandomImageCreator();
        ric.createArray();
        double[][] pa = ric.getPixelArray();
        ric.createImage(pa);
        File file = new File("/Users/davidjmyles/IdeaProjects/DV1/testimages/img_01.png");
        try {
            ImageIO.write(ric.getNewImage(), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputImage ii1 = new InputImage("/Users/davidjmyles/IdeaProjects/DV1/testimages/img_01.png",5, 5);
        BufferedImage bi1 = ii1.getInputImage();
        double [][] iia1 = ii1.createInputImageArray(bi1);
        Random r1 = new Random();
        int i1 = r1.nextInt(iia1.length);
        int i2 = r1.nextInt(iia1[0].length);
        assertEquals(pa.length, iia1.length);
        assertEquals(pa[0].length, iia1[0].length);
        assertEquals(pa [i1][i1], iia1 [i1][i1]);
        assertEquals(pa [i1][i2], iia1 [i1][i2]);
        assertEquals(pa [i2][i1], iia1 [i2][i1]);
        assertEquals(pa [i2][i2], iia1 [i2][i2]);

    }
}