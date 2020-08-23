package TestFiles;

import ApplicationFiles.*;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for ArrayProcessor class
 */
class ArrayProcessorTest {

    /**
     * Creates 3 random images, places pixels into 1D arrays, which form the columns of a matrix. Rows values are added
     * together to make average for each pixel. Assert statements verify that each row has correctly calculated average.
     */
    @Test
    void getAvgValuesTest() {
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

    /**
     * Images, arrays, matrix & avg values created as per getAvgValuesTest(). Each value then has its average row value
     * subtracted. A row is randomly selected, and the assert statements verify that each value in that row has had the
     * row average correctly subtracted
     */
    @Test
    void applyAvgValuesTest() {
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
        Random r1 = new Random();
        int a = r1.nextInt(3);
        double b = a2rrm.getEntry(a,0);
        double c = a2rrm.getEntry(a,1);
        double d = a2rrm.getEntry(a,2);
        double e = (b+c+d)/3;
        ap.applyAvgValues(a2rrm);
        assertEquals(b-e, a2rrm.getEntry(a,0), 1);
        assertEquals(c-e, a2rrm.getEntry(a,1), 1);
        assertEquals(d-e, a2rrm.getEntry(a,2), 1);
    }

    /**
     * Steps from applyAvgValuesTest() completed. Once matrix has been normalised, a 2D array is created with matching
     * values and the final column (which contained avg values) deleted. Test checks that the array has the same number
     * or rows as the normalised matrix, but 1 fewer columns.
     */
    @Test
    void finaliseAvgValuesTest() {
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
        assertEquals(a2rrm.getRowDimension(), d7.length);
        assertEquals(a2rrm.getColumnDimension()-1, d7[0].length);
    }

    /**
     * All steps from calculateImageWeightsTest() completed. Matrix with pixel weightings is turned into 2D array with
     * an extra row at the end. Sum of the values from each column are added to this row. Test checks that the sum of
     * all values in a randomly selected column from the Matrix match the value in the last row of that column from the
     * array.
     */
    @Test
    void createWeightsArrayTest() {
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
        ap.getAvgValues(a2rrm, 3);
        ap.applyAvgValues(a2rrm);
        ap.finaliseAvgValues(a2rrm);
        double [][] d7 = ap.finaliseAvgValues(a2rrm);
        SingularValueDecomposition svd1 = new SingularValueDecomposition(a2rrm);
        RealMatrix r1 = svd1.getU();
        ap.createPCMatrix(81,12);
        ap.applyTrainingVectors(d7,r1,1,0);
        ap.applyTrainingVectors(d7,r1,2,4);
        ap.applyTrainingVectors(d7,r1,3,8);
        RealMatrix r2 = ap.getPCMatrix();
        double [][] d8 = ap.createWeightsArray(r2);
        Random rand1 = new Random();
        int a1 = rand1.nextInt(12);
        double a = 0;
        for (int i = 0; i < r2.getRowDimension(); i++)    {
            a = a + r2.getEntry(i,a1);
        }
        double b = d8[d8.length-1][a1];
        assertEquals(a,b);
    }

    /**
     * 3 images from file processed for average values and vectors generation. 4 vectors then applied to the images.
     * Test takes 3 positions on final array and checks that the values match normalised array value multiplied by vector
     * value at those positions.
     * @throws SQLException
     */
    @Test
    void applyTrainingVectorsTest() throws SQLException, IOException {
        ArrayProcessor ap = new ArrayProcessor();
        ImageMatrix im = new ImageMatrix();
        FileManager fm = new FileManager();
        im.createTrainingRealMatrix(442368, 4);
        Array2DRowRealMatrix baseMatrix = im.getTrainingMatrix();
        Image i = new Image();
        BufferedImage bi = i.inputImage("IrisImages(576x768)/001L_1.png",768,567);
        double [][] d1 = i.createInputImageArray(bi);
        ap.flattenArray(d1);
        double [] d2 = ap.getFlatArray();
        Image i2 = new Image();
        BufferedImage bi2 = i2.inputImage("IrisImages(576x768)/002L_1.png",768,567);
        double [][] d3 = i2.createInputImageArray(bi2);
        ap.flattenArray(d3);
        double [] d4 = ap.getFlatArray();
        Image i3 = new Image();
        BufferedImage bi3 = i3.inputImage("IrisImages(576x768)/003L_1.png",768,567);
        double [][] d5 = i3.createInputImageArray(bi3);
        ap.flattenArray(d5);
        double [] d6 = ap.getFlatArray();
        im.populateTrainingMatrix(baseMatrix,d2,0);
        im.populateTrainingMatrix(baseMatrix,d4,1);
        im.populateTrainingMatrix(baseMatrix,d6,2);
        ap.getAvgValues(baseMatrix,3);
        ap.applyAvgValues(baseMatrix);
        ap.finaliseAvgValues(baseMatrix);//Needed????
        double [][] d11 = ap.finaliseAvgValues(baseMatrix);
        im.createImageRealMatrix(d1);
        Array2DRowRealMatrix a2rrm = im.getTrainingMatrix();
        SingularValueDecomposition svd1 = new SingularValueDecomposition(a2rrm);
        RealMatrix r1 = svd1.getU();
        double [][] dr = fm.reduceVectors(r1);
        ap.createPCMatrix(442368,(12));
        ap.applyTrainingVectors(d11,r1,1,0);
        ap.applyTrainingVectors(d11,r1,2,4);
        ap.applyTrainingVectors(d11,r1,3,8);
        Array2DRowRealMatrix r2 = ap.getPCMatrix();
        double d14 [][] = r2.getData();
        assertEquals(d11[0][0]*dr[0][0],d14[0][0]);
        assertEquals(d11[2][1]*dr[2][1],d14[2][1]);
        assertEquals(d11[4][2]*dr[4][2],d14[4][2]);
        assertEquals(d11[6][2]*dr[6][2],d14[6][2]);
    }

    /**
     * Simulates running a test image. Image is normalised and multiplied by saved vectors. Test verifies that final
     * result at 3 positions matches the corresponding values for normalised array multiplied by vectors
     * @throws IOException
     */
    @Test
    void applyTestVectorsTest() throws IOException {
        FileManager fm = new FileManager();
        ArrayProcessor ap = new ArrayProcessor();
        UnknownImage ui = new UnknownImage();
        double[] p1 = ui.unknownImageInput("IrisImages(576x768)/001L_1.png",768,576,0,0, 768,576);
        int pixelCount = p1.length;
        double[] p2 = fm.retrieveAvgValuesFromFile("saveData/AvgValues", (pixelCount));
        double[] p3 = ui.applyAvgToUnkown(p1, p2);
        double[][] p4 = fm.retrieveVectorsFromFile("saveData/Vectors", (pixelCount));
        Array2DRowRealMatrix rm = new Array2DRowRealMatrix(p4);
        ap.createPCMatrix(pixelCount,4);
        Array2DRowRealMatrix rm2 = ap.getPCMatrix();
        ap.applyTestVectors(p3,rm);
        assertEquals(rm2.getRowDimension(),p3.length);
        assertEquals(rm2.getEntry(0,0), p3[0]*rm.getEntry(0,0));
        assertEquals(rm2.getEntry(300,2), p3[300]*rm.getEntry(300,2));
        assertEquals(rm2.getEntry(25000,3), p3[25000]*rm.getEntry(25000,3));
    }

    /**
     * Uses saved vectors as dummy 2D array. Creates weights table from the array and verifies that the final row from
     * the array (which would be the weight) is in the correct position in the returned table.
     * @throws IOException
     */
    @Test
    void createWeightsTableTest() throws IOException {
        FileManager fm = new FileManager();
        ArrayProcessor ap = new ArrayProcessor();
        double [][] d1 = fm.retrieveVectorsFromFile("saveData/Vectors", 442368);
        double [][] d2 = ap.createWeightsTable(d1,4);
        int a = d1.length-1;
        assertEquals(d1[a][0],d2[0][0]);
        assertEquals(d1[a][1],d2[0][1]);
    }
}