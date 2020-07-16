package TestFiles;

import ApplicationFiles.ArrayProcessor;
import ApplicationFiles.ImageMatrix;
import ApplicationFiles.RandomImage;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for creation/population of RealMatrix from CreateRealMatrix class
 */
class CreateRealMatrixTest {

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
        crm.PopulateTrainingMatrix(a2rrm, d2, 0);
        crm.PopulateTrainingMatrix(a2rrm, d4, 1);
        assertEquals(d2[2], a2rrm.getEntry(2,0));
        assertEquals(d2[4], a2rrm.getEntry(4,0));
        assertEquals(d2[6], a2rrm.getEntry(6,0));
        assertEquals(d4[1], a2rrm.getEntry(1,1));
        assertEquals(d4[5], a2rrm.getEntry(5,1));
        assertEquals(d4[7], a2rrm.getEntry(7,1));
    }

    @Test
    void getAvgValuesTest() {
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
        RandomImage ric3 = new RandomImage();
        ric3.createArray();
        double [][] d5 = ric2.getPixelArray();
        ric2.flattenArray(d5);
        double [] d6 = ric2.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.PopulateTrainingMatrix(a2rrm, d2, 0);
        crm.PopulateTrainingMatrix(a2rrm, d4, 1);
        crm.PopulateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm);
        assertEquals(((d2[0]+d4[0]+d6[0])/3), a2rrm.getEntry(0,3), 1);
        assertEquals(((d2[1]+d4[1]+d6[1])/3), a2rrm.getEntry(1,3), 1);
        assertEquals(((d2[2]+d4[2]+d6[2])/3), a2rrm.getEntry(2,3), 1);
        assertEquals(((d2[3]+d4[3]+d6[3])/3), a2rrm.getEntry(3,3), 1);
        assertEquals(((d2[4]+d4[4]+d6[4])/3), a2rrm.getEntry(4,3), 1);
        assertEquals(((d2[5]+d4[5]+d6[5])/3), a2rrm.getEntry(5,3), 1);
        assertEquals(((d2[6]+d4[6]+d6[6])/3), a2rrm.getEntry(6,3), 1);
        assertEquals(((d2[7]+d4[7]+d6[7])/3), a2rrm.getEntry(7,3), 1);
        assertEquals(((d2[8]+d4[8]+d6[8])/3), a2rrm.getEntry(8,3), 1);
    }

    @Test
    void applyAvgValues() {
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
        RandomImage ric3 = new RandomImage();
        ric3.createArray();
        double [][] d5 = ric2.getPixelArray();
        ric2.flattenArray(d5);
        double [] d6 = ric2.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.PopulateTrainingMatrix(a2rrm, d2, 0);
        crm.PopulateTrainingMatrix(a2rrm, d4, 1);
        crm.PopulateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm);
        ap.finaliseAvgValues(a2rrm);
        double [][] d7 = ap.finaliseAvgValues(a2rrm);
        assertEquals(a2rrm.getRowDimension(), d7.length);
        assertEquals(a2rrm.getColumnDimension()-1, d7[0].length);
    }

    @Test
    void finaliseAvgValues() {
    }

    @Test
    void addImageDataset() {
    }

}