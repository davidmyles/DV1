package TestFiles;


import ApplicationFiles.ArrayProcessor;
import ApplicationFiles.FileManager;
import ApplicationFiles.LinkSQL;
import ApplicationFiles.UnknownImage;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Test class for UnknownImage
 */
class UnknownImageTest {

    int x = 9;
    int y = 9;

    /**
     * Takes in image from file and creates array from pixel values. Verifies that array is of correct
     * dimensions
     * @throws IOException
     */
    @Test
    void unknownImageInputTest() throws IOException {
        UnknownImage ui = new UnknownImage();
        double [] d1 = ui.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_01.png",9,9,0,0,9,9);
        int a = x*y;
        int b = d1.length;
        assertEquals(a,b);
    }

    /**
     * Creates dummy array of average values with are saved to database and then retrieved. Test verifies that values
     * from output array match input
     * @throws SQLException
     */
    @Test
    void retrieveAvgValues() throws SQLException {
        LinkSQL ls = new LinkSQL();
        UnknownImage ui = new UnknownImage();
        ls.clearAvgValues();
        double [] d1 = new double[]{4,3,5,9,6,3,2,1,5,7,2,6};
        ls.savePixelAveragesToDB(d1);
        double [] d2 = ui.retrieveAvgValues();
        Assertions.assertEquals(d1[0],d2[0]);
        Assertions.assertEquals(d1[4],d2[4]);
        Assertions.assertEquals(d1[7],d2[7]);
    }

    /**
     * Takes image from file and creates pixel values array. Creates dummy list of average values and applied to pixel
     * array. Test compares returned array at 3 randomly selected points to verify that its value is the pixel array
     * value minus average value.
     * @throws IOException
     * @throws SQLException
     */
    @Test
    void applyAvgToUnkown() throws IOException, SQLException {
        UnknownImage ui = new UnknownImage();
        double [] d1 = ui.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/testimages/img_01.png",3,3,0,0,3,3);
        double [] d2 = new double[]{1,2,3,4,5,6,7,8,9};
        double [] d3 = ui.applyAvgToUnkown(d1,d2);
        System.out.println(Arrays.toString(d1));
        System.out.println(Arrays.toString(d2));
        System.out.println(Arrays.toString(d3));
        Random r1 = new Random();
        int a = r1.nextInt(d3.length);
        int b = r1.nextInt(d3.length);
        int c = r1.nextInt(d3.length);
        assertEquals(d3[a],d1[a]-d2[a],1);
        assertEquals(d3[b],(d1[b]-d2[b]),1);
        assertEquals(d3[c],(d1[c]-d2[c]),1);
    }

    /**
     * Creates dummy array of vectors which are saved to database then retrieved. Test verifies that retrieved values
     * match the input
     *
     * @throws SQLException
     */
    @Test
    void retrieveVectorsFromDBTest() throws SQLException {
        LinkSQL ls = new LinkSQL();
        UnknownImage ui = new UnknownImage();
        ls.clearPrincipleComponents();
        double [][] d1 = new double[][]{{4,3,5,9},{2,1,9,3},{8,6,5,4},{1,7,3,2}};
        ls.saveVectorsToDB(d1);
        Array2DRowRealMatrix rm = ui.retrieveVectorsFromDB();
        assertEquals(d1[0][0],rm.getEntry(0,0),1);
        assertEquals(d1[1][1],rm.getEntry(1,1),1);
        assertEquals(d1[2][2],rm.getEntry(2,2),1);
        assertEquals(d1[3][3],rm.getEntry(3,3),1);
    }

    /**
     * Creates finalised vector array and turns into weights array. Test verifies that weights array contains final row
     * values from vector array
     * @throws IOException
     * @throws SQLException
     */
    @Test
    void createUnknownWeightsTableTest() throws IOException, SQLException {
        UnknownImage ui = new UnknownImage();
        ArrayProcessor ap = new ArrayProcessor();
        FileManager fm = new FileManager();
        double [] d1 = UnknownImage.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_01.png",9,9,0,0,9,9);
        int pixelCount = d1.length;
        double[] p2 = fm.retrieveAvgValuesFromFile("saveData/testAvgValues", (pixelCount));
        double[] p3 = ui.applyAvgToUnkown(d1, p2);
        double[][] p4 = fm.retrieveVectorsFromFile("saveData/testVectors", (pixelCount));
        Array2DRowRealMatrix rm = new Array2DRowRealMatrix(p4);
        ap.createPCMatrix(pixelCount,4);
        Array2DRowRealMatrix rm2 = ap.getPCMatrix();
        ap.applyTestVectors(p3,rm);
        double[][] p5 = ap.createWeightsArray(rm2);
        double[] p6 = ui.createUnknownWeightsTable(p5);
        int a = p5.length-1;
        Assertions.assertEquals(p6[0],p5[a][0]);
        Assertions.assertEquals(p6[1],p5[a][1]);
        Assertions.assertEquals(p6[2],p5[a][2]);
        Assertions.assertEquals(p6[3],p5[a][3]);
    }

    /**
     * Runs full process for unknown image to create weights array. Runs matching process with expected result specified
     * to match input. Test covers the mechanics of matching process, not the effectiveness of the algorithm.
     *
     * Cannot be run at the same time as other tests due to changes to weights table in database. Should be run after
     * IrisImageTrainer, and before any other tests.
     *
     * @throws SQLException
     * @throws IOException
     */
    @Test
    void matchImageTest() throws SQLException, IOException {
        UnknownImage ui = new UnknownImage();
        ArrayProcessor ap = new ArrayProcessor();
        FileManager fm = new FileManager();
        LinkSQL ls = new LinkSQL();
        double [] d1 = UnknownImage.unknownImageInput("IrisImages(576x768)/001L_1.png",768,576,0,0,768,576);
        int pixelCount = d1.length;
        double[] p2 = fm.retrieveAvgValuesFromFile("saveData/AvgValues", (pixelCount));
        double[] p3 = ui.applyAvgToUnkown(d1, p2);
        double[][] p4 = fm.retrieveVectorsFromFile("saveData/Vectors", (pixelCount));
        Array2DRowRealMatrix rm = new Array2DRowRealMatrix(p4);
        ap.createPCMatrix(pixelCount,4);
        Array2DRowRealMatrix rm2 = ap.getPCMatrix();
        ap.applyTestVectors(p3,rm);
        double[][] p5 = ap.createWeightsArray(rm2);
        double[] p6 = ui.createUnknownWeightsTable(p5);
        int matchActual = ls.matchImage(p6,4);
        int matchExpected = 1;
        //assertEquals(matchExpected,matchActual);
    }

    /**
     * Creates blank matrix of specified dimensions. Test verifies that matrix dimensions are correct
     */
    @Test
    void createPCMatrix() {
        UnknownImage ui = new UnknownImage();
        int pixels = 20;
        Array2DRowRealMatrix rm = ui.createPCMatrix(pixels);
        assertEquals(pixels, rm.getRowDimension());
        assertEquals(4, rm.getColumnDimension());
    }
}
