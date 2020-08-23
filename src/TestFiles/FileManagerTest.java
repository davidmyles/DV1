package TestFiles;

import ApplicationFiles.*;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Test class for FileManager objects
 */
class FileManagerTest {

    /**
     * Creates images then processes to produce corresponding array of vectors. Vectors then transferred into new with
     * only the first 4 columns. Test verifies that final array is of correct dimensions, and that values are correct at
     * 2 randomly selected points.
     */
    @Test
    void reduceVectorsTest() {
        FileManager fm = new FileManager();
        ArrayProcessor ap = new ArrayProcessor();
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ap.flattenArray(d1);
        double [] d2 = ap.getFlatArray();
        RandomImage ric2 = new RandomImage();
        ric2.createArray();
        double [][] d3 = ric2.getPixelArray();
        ap.flattenArray(d3);
        double [] d4 = ap.getFlatArray();
        RandomImage ric3 = new RandomImage();
        ric3.createArray();
        double [][] d5 = ric3.getPixelArray();
        ap.flattenArray(d5);
        double [] d6 = ap.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        int x = d2.length;
        crm.createTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.populateTrainingMatrix(a2rrm, d2, 0);
        crm.populateTrainingMatrix(a2rrm, d4, 1);
        crm.populateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm,3);
        ap.applyAvgValues(a2rrm);
        ap.finaliseAvgValues(a2rrm);
        double [][] d7 = ap.finaliseAvgValues(a2rrm);
        SingularValueDecomposition svd1 = new SingularValueDecomposition(a2rrm);
        RealMatrix r1 = svd1.getU();
        double [][] d8 = fm.reduceVectors(r1);
        Random rand = new Random();
        int a = rand.nextInt(81);
        int b = rand.nextInt(4);
        int c = rand.nextInt(81);
        int d = rand.nextInt(4);
        assertEquals(d8.length,r1.getRowDimension());
        assertEquals(d8[0].length,4);
        assertEquals(d8[a][b],r1.getEntry(a,b),1);
        assertEquals(d8[c][d],r1.getEntry(c,d),1);
    }

    /**
     * This test covers saveVectorsToFile() and retrieveVectorsFromFile() methods
     *
     * Process completed to create array of 4 vectors from training process. File then saved and retrieved. Test
     * verifies that dimensions of output & input arrays match, and also that values at 2 randomly selected points
     * match
     * @throws IOException
     */
    @Test
    void VectorsInputOutputTest() throws IOException {
        FileManager fm = new FileManager();
        ArrayProcessor ap = new ArrayProcessor();
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ap.flattenArray(d1);
        double [] d2 = ap.getFlatArray();
        RandomImage ric2 = new RandomImage();
        ric2.createArray();
        double [][] d3 = ric2.getPixelArray();
        ap.flattenArray(d3);
        double [] d4 = ap.getFlatArray();
        RandomImage ric3 = new RandomImage();
        ric3.createArray();
        double [][] d5 = ric3.getPixelArray();
        ap.flattenArray(d5);
        double [] d6 = ap.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        int x = d2.length;
        crm.createTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.populateTrainingMatrix(a2rrm, d2, 0);
        crm.populateTrainingMatrix(a2rrm, d4, 1);
        crm.populateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm,3);
        ap.applyAvgValues(a2rrm);
        ap.finaliseAvgValues(a2rrm);
        double [][] d7 = ap.finaliseAvgValues(a2rrm);
        SingularValueDecomposition svd1 = new SingularValueDecomposition(a2rrm);
        RealMatrix r1 = svd1.getU();
        double [][] d8 = fm.reduceVectors(r1);
        fm.saveVectorsToFile(d8, "saveData/testVectors");
        double [][] d9 = fm.retrieveVectorsFromFile("saveData/testVectors",81);
        Random rand = new Random();
        int a = rand.nextInt(81);
        int b = rand.nextInt(4);
        int c = rand.nextInt(81);
        int d = rand.nextInt(4);
        assertEquals(d8.length,d9.length);
        assertEquals(d8[0].length,d9[0].length);
        assertEquals(d8[a][b],d9[a][b],1);
        assertEquals(d8[c][d],d9[c][d],1);
    }

    /**
     * This test covers saveAvgValuesToFile() and retrieveAvgValuesFromFile() methods
     *
     * Process completed to create array of average values from training process. File then saved and retrieved. Test
     * verifies that dimensions of output & input arrays match, and also that values at 2 randomly selected points
     * match
     * @throws IOException
     */
    @Test
    void avgValuesInputOutputTest() throws IOException {
        FileManager fm = new FileManager();
        ArrayProcessor ap = new ArrayProcessor();
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ap.flattenArray(d1);
        double [] d2 = ap.getFlatArray();
        RandomImage ric2 = new RandomImage();
        ric2.createArray();
        double [][] d3 = ric2.getPixelArray();
        ap.flattenArray(d3);
        double [] d4 = ap.getFlatArray();
        RandomImage ric3 = new RandomImage();
        ric3.createArray();
        double [][] d5 = ric3.getPixelArray();
        ap.flattenArray(d5);
        double [] d6 = ap.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        int x = d2.length;
        crm.createTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.populateTrainingMatrix(a2rrm, d2, 0);
        crm.populateTrainingMatrix(a2rrm, d4, 1);
        crm.populateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm,3);
        double [] d7 = ap.getTrainingAvgValues(a2rrm);
        fm.saveAvgValuesToFile(d7, "saveData/testAvgValues");
        double [] d8 = fm.retrieveAvgValuesFromFile("saveData/testAvgValues",81);
        Random rand = new Random();
        int a = rand.nextInt(81);
        int b = rand.nextInt(81);
        assertEquals(d7.length,d8.length);
        assertEquals(d7[a],d8[a],1);
        assertEquals(d7[b],d8[b],1);
    }
}