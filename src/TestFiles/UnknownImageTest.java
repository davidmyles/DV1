package TestFiles;

import ApplicationFiles.ArrayProcessor;
import ApplicationFiles.LinkSQL;
import ApplicationFiles.UnknownImage;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class UnknownImageTest {

    int x = 3;
    int y = 3;

    @Test
    void unknownImageProcessingTest() throws IOException {
        UnknownImage ui = new UnknownImage();
        double [] d1 = ui.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png",x,y);
        int a = x*y;
        int b = d1.length;
        assertEquals(a,b);
    }

    /**
     * JUnit when retrieving from database
     */
    @Test
    void retrieveAvgValues() {
    }

    //@Test
    //void applyAvgUnkown() throws IOException, SQLException {
       // UnknownImage ui = new UnknownImage();
        //double [] d1 = ui.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png",x,y);
        //double [] d2 = ui.retrieveAvgValues();
        //double [] d3 = ui.applyAvgUnkown(d1,d2);
        //Random r1 = new Random();
        //int a = r1.nextInt(d3.length);
        //int b = r1.nextInt(d3.length);
        //int c = r1.nextInt(d3.length);
        //assertEquals(d3[a],d1[a]-d2[a],1);
        //assertEquals(d3[b],(d1[b]-d2[b]),1);
        //assertEquals(d3[c],(d1[c]-d2[c]),1);
    //}

    /**
     * test needed?
     */
    @Test
    void retrievePrincipleComponentsX2() {
    }

    @Test
    void unknownImageWeightsTest() throws IOException, SQLException {
        UnknownImage ui = new UnknownImage();
        double [] d1 = ui.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png",x,y);
        double [] d2 = ui.retrieveAvgValues();
        double [] d3 = ui.applyAvgUnkown(d1,d2);
        Array2DRowRealMatrix r1 = ui.retrievePrincipleComponentsX2();
        ui.createPCMatrix();
        Array2DRowRealMatrix rm2 = ui.getWeightsMatrix();
        ui.unknownImageWeightsPCx2(d3,r1,0);
        ui.unknownImageWeightsPCx2(d3,r1,1);
        ArrayProcessor ap = new ArrayProcessor();
        double [][] p4 = ap.createWeightsArray(rm2);
        Assertions.assertEquals(d3[0]*r1.getEntry(0,0), p4[0][0],1);
        Assertions.assertEquals(d3[4]*r1.getEntry(4,1), p4[4][1],1);
        Assertions.assertEquals(d3[8]*r1.getEntry(8,1), p4[8][1],1);
    }

    @Test
    void matchImageTest() throws SQLException, IOException {
        UnknownImage ui = new UnknownImage();
        LinkSQL ls = new LinkSQL();
        ls.clearUnknownImageWeights();
        double [] p1 = ui.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png",3,3);
        double [] p2 = ui.retrieveAvgValues();
        double [] p3 = ui.applyAvgUnkown(p1,p2);
        Array2DRowRealMatrix rm = ui.retrievePrincipleComponentsX2();
        ui.createPCMatrix();
        Array2DRowRealMatrix rm2 = ui.getWeightsMatrix();
        ui.unknownImageWeightsPCx2(p3,rm,0);
        ui.unknownImageWeightsPCx2(p3,rm,1);
        System.out.println("Weights matrix" + Arrays.deepToString(rm2.getData()));
        ArrayProcessor ap = new ArrayProcessor();
        double [][] p4 = ap.createWeightsArray(rm2);
        System.out.println("Weights matrix with sum" + Arrays.deepToString(p4));
        double [][] p5 = ui.createUnknownWeightsTable(p4);
        System.out.println("Weights for unknown image" + Arrays.deepToString(p5));
        ls.saveUnkownWeightsToDB(p5);
        String matchActual = ls.matchImage();
        String matchExpected = "image_1";
        assertEquals(matchExpected,matchActual);
    }

    @Test
    void matchAlteredImageTest() throws SQLException, IOException {
        UnknownImage ui = new UnknownImage();
        LinkSQL ls = new LinkSQL();
        ls.clearUnknownImageWeights();
        double [] p1 = ui.unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png",3,3);
         double [] px = new double [p1.length];
            for (int i = 0; i < p1.length; i++) {
                px[i]=p1[i]+1;
                }
        double [] p2 = ui.retrieveAvgValues();
        double [] p3 = ui.applyAvgUnkown(px,p2);
        Array2DRowRealMatrix rm = ui.retrievePrincipleComponentsX2();
        ui.createPCMatrix();
        Array2DRowRealMatrix rm2 = ui.getWeightsMatrix();
        ui.unknownImageWeightsPCx2(p3,rm,0);
        ui.unknownImageWeightsPCx2(p3,rm,1);
        System.out.println("Weights matrix" + Arrays.deepToString(rm2.getData()));
        ArrayProcessor ap = new ArrayProcessor();
        double [][] p4 = ap.createWeightsArray(rm2);
        System.out.println("Weights matrix with sum" + Arrays.deepToString(p4));
        double [][] p5 = ui.createUnknownWeightsTable(p4);
        System.out.println("Weights for unknown image" + Arrays.deepToString(p5));
        ls.saveUnkownWeightsToDB(p5);
        String matchActual = ls.matchImage();
        String matchExpected = "image_1";
        assertEquals(matchExpected,matchActual);
    }

}