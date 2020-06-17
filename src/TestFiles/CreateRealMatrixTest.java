package TestFiles;

import ApplicationFiles.CreateRealMatrix;
import ApplicationFiles.RandomImageCreator;
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
        RandomImageCreator ric1 = new RandomImageCreator();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        CreateRealMatrix crm1 = new CreateRealMatrix(d1);
        Array2DRowRealMatrix r2rrm = crm1.getRealMatrix();
        assertEquals(d1.length, r2rrm.getRowDimension());
        assertEquals(d1[0].length, r2rrm.getColumnDimension());

    }

    /**
     * Creates array & RealMatrix. Selects a random element from array and tests that corresponding value in RealMatrix
     * is the same
     */
    @Test
    void populateRealMatrixTest() {
        RandomImageCreator ric1 = new RandomImageCreator();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        CreateRealMatrix crm1 = new CreateRealMatrix(d1);
        Array2DRowRealMatrix r2rrm = crm1.getRealMatrix();
        Random random1 = new Random();
        int i1 = random1.nextInt(5);
        int i2 = random1.nextInt(5);
        assertEquals(d1[i1][i2], r2rrm.getEntry(i1,i2));
    }
}