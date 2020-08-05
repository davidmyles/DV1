package TestFiles;

import ApplicationFiles.ImageMatrix;
import ApplicationFiles.RandomImage;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for creation/population of RealMatrix from CreateRealMatrix class
 */
class ImageMatrixTest {

    /**
     * Creates array and RealMatrix. Tests that dimensions of both collections are matching
     */
    @Test
    void createRealMatrixTest() {
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ImageMatrix crm1 = new ImageMatrix();
        crm1.CreateImageRealMatrix(d1);
        Array2DRowRealMatrix r2rrm = crm1.getImageMatrix();
        assertEquals(d1.length, r2rrm.getRowDimension());
        assertEquals(d1[0].length, r2rrm.getColumnDimension());

    }

    /**
     * Creates array & RealMatrix. Selects a random element from array and tests that corresponding value in RealMatrix
     * is the same
     */
    @Test
    void populateRealMatrixTest() {
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ImageMatrix crm1 = new ImageMatrix();
        crm1.CreateImageRealMatrix(d1);
        Array2DRowRealMatrix r2rrm = crm1.getImageMatrix();
        Random random1 = new Random();
        int i1 = random1.nextInt(3);
        int i2 = random1.nextInt(3);
        assertEquals(d1[i1][i2], r2rrm.getEntry(i1,i2));
    }

    @Test
    void populateTrainingMatrixTest() {
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ric1.flattenArray(d1);
        double [] d2 = ric1.getFlatArray();
        RandomImage ric2 = new RandomImage();
        ric2.createArray();
        double [][] d3 = ric2.getPixelArray();
        ric2.flattenArray(d3);
        double [] d4 = ric2.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 3);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.populateTrainingMatrix(a2rrm, d2, 0);
        crm.populateTrainingMatrix(a2rrm, d4, 1);
        assertEquals(d2[2], a2rrm.getEntry(2,0));
        assertEquals(d2[4], a2rrm.getEntry(4,0));
        assertEquals(d2[6], a2rrm.getEntry(6,0));
        assertEquals(d4[1], a2rrm.getEntry(1,1));
        assertEquals(d4[5], a2rrm.getEntry(5,1));
        assertEquals(d4[7], a2rrm.getEntry(7,1));
    }

}