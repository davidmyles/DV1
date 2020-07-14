package TestFiles;

import ApplicationFiles.CreateRealMatrix;
import ApplicationFiles.RandomImageCreator;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for SVD creation from CreateSVD class
 */
class CreateSVDTest {

    /**
     * Creates array and RealMatrix. Creates SVD object using RealMatrix. Tests that an output array containing
     * singular values is produced
     */
    @Test
    void getSVDTest() {
        RandomImageCreator ric1 = new RandomImageCreator();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        CreateRealMatrix crm1 = new CreateRealMatrix();
        crm1.CreateImageRealMatrix(d1);
        Array2DRowRealMatrix r2rrm = crm1.getImageMatrix();
        SingularValueDecomposition svd1 = new SingularValueDecomposition(r2rrm);
        double [] d2 = svd1.getSingularValues();
        assertNotEquals(0, d2.length);
    }

    /**
     * Generates singular values array as above. Tests that array elements are in descending value order
     */
    @Test
    void testSVDValues() {
        RandomImageCreator ric1 = new RandomImageCreator();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        CreateRealMatrix crm1 = new CreateRealMatrix();
        crm1.CreateImageRealMatrix(d1);
        Array2DRowRealMatrix r2rrm = crm1.getImageMatrix();
        SingularValueDecomposition svd1 = new SingularValueDecomposition(r2rrm);
        double [] d2 = svd1.getSingularValues();
        assertTrue(d2[0]>d2[1]);
        assertTrue(d2[1]>d2[2]);
        //assertTrue(d2[2]>d2[3]);
        //assertTrue(d2[3]>d2[4]);
    }
}