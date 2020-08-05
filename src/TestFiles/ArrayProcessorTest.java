package TestFiles;

import ApplicationFiles.ArrayProcessor;
import ApplicationFiles.ImageMatrix;
import ApplicationFiles.RandomImage;
import ApplicationFiles.SVD;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.junit.jupiter.api.Test;

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
        double [][] d5 = ric3.getPixelArray();
        ric3.flattenArray(d5);
        double [] d6 = ric3.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
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
        double [][] d5 = ric3.getPixelArray();
        ric3.flattenArray(d5);
        double [] d6 = ric3.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
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
        double [][] d5 = ric3.getPixelArray();
        ric3.flattenArray(d5);
        double [] d6 = ric3.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
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
     * All steps from finaliseAvgValuesTest() completed. Normalised array is subjected to SVD, with the U matrix
     * returned. Each value from the array is multiplied by the 1st 2 principle components of the U matrix. Assert
     * statements check that 3 selected values from the final returned matrix match the corresponding value from the
     * input array, multiplied by the relevant principle component
     */
    @Test
    void calculateImageWeightsTest() {
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
        double [][] d5 = ric3.getPixelArray();
        ric3.flattenArray(d5);
        double [] d6 = ric3.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.populateTrainingMatrix(a2rrm, d2, 0);
        crm.populateTrainingMatrix(a2rrm, d4, 1);
        crm.populateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm,3);
        ap.applyAvgValues(a2rrm);
        ap.finaliseAvgValues(a2rrm);
        double [][] d7 = ap.finaliseAvgValues(a2rrm);
        SVD svd = new SVD();
        svd.CreateTrainingSVD(a2rrm);
        SingularValueDecomposition svd1 = svd.getTrainingSVD();
        RealMatrix r1 = svd1.getU();
        ap.createPCMatrix(9,6);
        ap.calculateImageWeights(d7,r1,0, 0, 0);
        ap.calculateImageWeights(d7,r1,0, 1, 1);
        ap.calculateImageWeights(d7,r1,1, 0, 2);
        ap.calculateImageWeights(d7,r1,1, 1, 3);
        ap.calculateImageWeights(d7,r1,2, 0, 4);
        ap.calculateImageWeights(d7,r1,2, 1, 5);
        RealMatrix r2 = ap.getPCMatrix();
        assertEquals(d7[0][0]*r1.getEntry(0,0), r2.getEntry(0,0),1);
        assertEquals(d7[4][1]*r1.getEntry(4,1), r2.getEntry(4,3),1);
        assertEquals(d7[8][2]*r1.getEntry(8,1), r2.getEntry(8,5),1);

    }

    /**
     * All steps from calculateImageWeightsTest() completed. Matrix with pixel weightings is turned into 2D array with
     * an extra row at the end. Sum of the values from each column are added to this row. Test checks that the sum of
     * all values in a randomly selected column from the Matrix match the value in the last row of that column from the
     * array.
     */
    @Test
    void createWeightsArrayTest() {
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
        double [][] d5 = ric3.getPixelArray();
        ric3.flattenArray(d5);
        double [] d6 = ric3.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.populateTrainingMatrix(a2rrm, d2, 0);
        crm.populateTrainingMatrix(a2rrm, d4, 1);
        crm.populateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm, 3);
        ap.applyAvgValues(a2rrm);
        ap.finaliseAvgValues(a2rrm);
        double [][] d7 = ap.finaliseAvgValues(a2rrm);
        SVD svd = new SVD();
        svd.CreateTrainingSVD(a2rrm);
        SingularValueDecomposition svd1 = svd.getTrainingSVD();
        RealMatrix r1 = svd1.getU();
        ap.createPCMatrix(9,6);
        ap.calculateImageWeights(d7,r1,0, 0, 0);
        ap.calculateImageWeights(d7,r1,0, 1, 1);
        ap.calculateImageWeights(d7,r1,1, 0, 2);
        ap.calculateImageWeights(d7,r1,1, 1, 3);
        ap.calculateImageWeights(d7,r1,2, 0, 4);
        ap.calculateImageWeights(d7,r1,2, 1, 5);
        RealMatrix r2 = ap.getPCMatrix();
        double [][] d8 = ap.createWeightsArray(r2);
        Random rand1 = new Random();
        int a1 = rand1.nextInt(6);
        double a = (r2.getEntry(0, a1)+r2.getEntry(1, a1)+r2.getEntry(2, a1)+r2.getEntry(3, a1)+
                r2.getEntry(4, a1)+r2.getEntry(5, a1)+r2.getEntry(6, a1)+r2.getEntry(7, a1)+
                r2.getEntry(8, a1));
        double b = d8[d8.length-1][a1];
        assertEquals(a,b);
    }

    @Test
    void getAvgValuesArrayTest() {
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
        double [][] d5 = ric3.getPixelArray();
        ric3.flattenArray(d5);
        double [] d6 = ric3.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.populateTrainingMatrix(a2rrm, d2, 0);
        crm.populateTrainingMatrix(a2rrm, d4, 1);
        crm.populateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm,3);
        double [][] d7 = ap.getArrayFromMatrix(a2rrm);
        double [] d8 = ap.getAvgValuesArray(d7);
        int a = d7[0].length-1;
        Random r1 = new Random();
        int b = r1.nextInt(d7.length-1);
        int c = r1.nextInt(d7.length-1);
        int d = r1.nextInt(d7.length-1);
        assertEquals(d8[b],d7[b][a],1);
        assertEquals(d8[c],d7[c][a],1);
        assertEquals(d8[d],d7[d][a],1);

    }

    /*@Test
    void getPCMatrix() throws SQLException {
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
        double [][] d5 = ric3.getPixelArray();
        ric3.flattenArray(d5);
        double [] d6 = ric3.getFlatArray();
        ImageMatrix crm = new ImageMatrix();
        ArrayProcessor ap = new ArrayProcessor();
        int x = d2.length;
        crm.CreateTrainingRealMatrix(x, 4);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        crm.PopulateTrainingMatrix(a2rrm, d2, 0);
        crm.PopulateTrainingMatrix(a2rrm, d4, 1);
        crm.PopulateTrainingMatrix(a2rrm, d6, 2);
        ap.getAvgValues(a2rrm,3);
        double [][] d7 = ap.getArrayFromMatrix(a2rrm);
        double [] d8 = ap.getAvgValuesArray(d7);
        LinkSQL ls = new LinkSQL();
        ls.clearUnknownImageWeights();
        ls.savePixelAveragesToDB(d8);
        SVD svd = new SVD();
        svd.CreateTrainingSVD(a2rrm);
        SingularValueDecomposition svd1 = svd.getTrainingSVD();
        RealMatrix r1 = svd1.getU();
        ap.applyAvgValues(a2rrm);
        ap.finaliseAvgValues(a2rrm);
        double [][] d9 = ap.finaliseAvgValues(a2rrm);
        ap.createPCMatrix(9,6);
        ap.calculateImageWeights(d9,r1,0, 0, 0);
        ap.calculateImageWeights(d9,r1,0, 1, 1);
        ap.calculateImageWeights(d9,r1,1, 0, 2);
        ap.calculateImageWeights(d9,r1,1, 1, 3);
        ap.calculateImageWeights(d9,r1,2, 0, 4);
        ap.calculateImageWeights(d9,r1,2, 1, 5);
        RealMatrix r2 = ap.getPCMatrix();
        System.out.println("Calculated Principle Components: " + Arrays.deepToString(r2.getData()));
        double[][] d10 = ap.createWeightsArray(r2);
        System.out.println("Weights Array: " + Arrays.deepToString(d5));
        ap.createWeightsTable(d10,2);
        double [][] d11 = ap.getWeightsTable();
        System.out.println("Weights Table: " + Arrays.deepToString(d11));
        ls.saveWeightsTableToDBx2PC(d11);
        int a = 9;
        UnknownImage ui = new UnknownImage();
        double [] d12 = ui.retrieveAvgValues();
        double [] d13 = ui.applyAvgUnkown(d2,d12);
        Array2DRowRealMatrix r3 = ui.retrievePrincipleComponentsx2();
        Array2DRowRealMatrix r4 = ui.createPCMatrix(9,6);
        ui.unknownImageWeightsPC(d13,r3,r4,0);
        ui.unknownImageWeightsPC(d13,r3,r4,1);
        double [][] d14 = ap.createWeightsArray(r4);
        System.out.println("Weights matrix with sum" + Arrays.deepToString(d14));
        double [][] d15 = ui.createUnknownWeightsTable(d14,2);
        System.out.println("Weights for unknown image" + Arrays.deepToString(d15));
        ls.saveUnkownWeightsToDBx2PC(d15);
        assertEquals(d10[0][0],d14[0][0]);
    }*/

    @Test
    void getWeightsTable() {
    }
}