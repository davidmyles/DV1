package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UnknownImage {

    static double[] d2;
    private static Array2DRowRealMatrix weightsMatrix;
    private static BufferedImage inputImage;

    public static double[] unknownImageInput(String inputfile, int w, int h) throws IOException {
        InputImage ii = new InputImage(inputfile, w, h);
        BufferedImage inputImage = ii.getInputImage();
        double[][] d1 = ii.createInputImageArray(inputImage);
        RandomImage ri = new RandomImage();
        ri.flattenArray(d1);
        d2 = ri.getFlatArray();
        System.out.println("original pixels" + Arrays.toString(d2));
        return d2;
    }

    public static double[] retrieveAvgValues() throws SQLException {
        ArrayList<Double> d3 = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("SELECT avg_pv FROM avg_values ORDER BY p_ID ASC");
        ResultSet r1 = ps.executeQuery();
        while (r1.next()) {
            double p = r1.getDouble(1);
            d3.add(p);
        }
        double[] d1 = new double[d3.size()];
        for (int i = 0; i < d1.length; i++) {
            d1[i] = d3.get(i);
        }
        System.out.println("average values from db" + Arrays.toString(d1));
        return d1;
    }

    public static double[] applyAvgUnkown(double[] pixels, double[] avgValues) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixels[i] - avgValues[i];
        }
        System.out.println("normalised pixel values" + Arrays.toString(pixels));
        return pixels;
    }

    public static Array2DRowRealMatrix retrievePrincipleComponentsx2() throws SQLException {
        ArrayList<Double> d1 = new ArrayList<>();
        ArrayList<Double> d2 = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("SELECT PC_1, PC_2 FROM principle_components");
        ResultSet r1 = ps.executeQuery();
        while (r1.next()) {
            double p1 = r1.getDouble(1);
            d1.add(p1);
            double p2 = r1.getDouble(2);
            d2.add(p2);
        }
        Array2DRowRealMatrix rM = new Array2DRowRealMatrix(d1.size(),2);
        double[] d3 = new double[d1.size()];
            for (int i = 0; i < d3.length; i++) {
            d3[i] = d1.get(i);
        }
        double[] d4 = new double[d2.size()];
            for (int i = 0; i < d3.length; i++)    {
            d4[i] = d2.get(i);
        }
        rM.setColumn(0, d3);
        rM.setColumn(1, d4);
        System.out.println("principle components" + Arrays.deepToString(rM.getData()));
        return rM;
    }

    public static Array2DRowRealMatrix retrievePrincipleComponentsx4() throws SQLException {
        ArrayList<Double> d1 = new ArrayList<>();
        ArrayList<Double> d2 = new ArrayList<>();
        ArrayList<Double> d3 = new ArrayList<>();
        ArrayList<Double> d4 = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("SELECT PC_1, PC_2, PC_3, PC_4 FROM principle_components ORDER BY P_ID ASC");
        ResultSet r1 = ps.executeQuery();
        while (r1.next()) {
            double p1 = r1.getDouble(1);
            d1.add(p1);
            double p2 = r1.getDouble(2);
            d2.add(p2);
            double p3 = r1.getDouble(3);
            d3.add(p3);
            double p4 = r1.getDouble(4);
            d4.add(p4);
        }
        Array2DRowRealMatrix rM = new Array2DRowRealMatrix(d1.size(),4);
        double[] d1a = new double[d1.size()];
        for (int i = 0; i < d1a.length; i++) {
            d1a[i] = d1.get(i);
        }
        double[] d2a = new double[d2.size()];
        for (int i = 0; i < d2a.length; i++)    {
            d2a[i] = d2.get(i);
        }
        double[] d3a = new double[d3.size()];
        for (int i = 0; i < d3a.length; i++) {
            d3a[i] = d3.get(i);
        }
        double[] d4a = new double[d4.size()];
        for (int i = 0; i < d4a.length; i++)    {
            d4a[i] = d4.get(i);
        }
        rM.setColumn(0, d1a);
        rM.setColumn(1, d2a);
        rM.setColumn(2, d3a);
        rM.setColumn(3, d4a);
        System.out.println("principle components" + Arrays.deepToString(rM.getData()));
        return rM;
    }

    public static Array2DRowRealMatrix unknownImageWeightsPC(double [] pixelArray, Array2DRowRealMatrix pc, Array2DRowRealMatrix blankMatrix, int x)   {
        double [] columnArray = new double[pixelArray.length];
        double [][] matrixArray = pc.getData();
        for (int i = 0; i < pixelArray.length; i++) {
            columnArray[i] = pixelArray[i];
        }
        for (int i = 0; i < columnArray.length; i++) {
            columnArray[i] = columnArray[i]*matrixArray[i][x];
            blankMatrix.setColumn(x,columnArray);
        }
        return blankMatrix;
    }

    public static double [][] createUnknownWeightsTable(double [][] inputArray, int pc)  {
        double [][] weightsTable = new double[1][pc];
        int a = inputArray.length-1;
        weightsTable[0][0] = inputArray[a][0];
        weightsTable[0][1] = inputArray[a][1];
        weightsTable[0][2] = inputArray[a][2];
        weightsTable[0][3] = inputArray[a][3];
        return weightsTable;
    }

    public static void setInputImage(BufferedImage inputImage) {
        UnknownImage.inputImage = inputImage;
    }

    public BufferedImage getInputImage()    {
        return inputImage;
    }

    public static Array2DRowRealMatrix createPCMatrix(int pixels, int pc)    {
        weightsMatrix = new Array2DRowRealMatrix(pixels,pc);
        return weightsMatrix;
    }

    public static Array2DRowRealMatrix getWeightsMatrix()  {
        return weightsMatrix;
    }


    public static void main(String args[]) throws SQLException, IOException {
            LinkSQL ls = new LinkSQL();
            ls.clearUnknownImageWeights();
            double [] p1 = unknownImageInput("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_09.png",50,50);
            double [] p2 = retrieveAvgValues();
            double [] p3 = applyAvgUnkown(p1,p2);
            Array2DRowRealMatrix rm = retrievePrincipleComponentsx4();
            Array2DRowRealMatrix rm2 = createPCMatrix(2500,4);
            unknownImageWeightsPC(p3,rm,rm2,0);
            unknownImageWeightsPC(p3,rm,rm2,1);
            unknownImageWeightsPC(p3,rm,rm2,2);
            unknownImageWeightsPC(p3,rm,rm2,3);
            System.out.println("Weights matrix" + Arrays.deepToString(rm2.getData()));
            ArrayProcessor ap = new ArrayProcessor();
            double [][] p4 = ap.createWeightsArray(rm2);
            System.out.println("Weights matrix with sum" + Arrays.deepToString(p4));
            double [][] p5 = createUnknownWeightsTable(p4,4);
            System.out.println("Weights for unknown image" + Arrays.deepToString(p5));
            ls.saveUnkownWeightsToDBx4PC(p5);
            ls.matchImage();
    }
}
