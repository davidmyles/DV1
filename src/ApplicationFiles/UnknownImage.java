package ApplicationFiles;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UnknownImage extends Image {

    static double[] imageArray;
    private static Array2DRowRealMatrix weightsMatrix;
    private static BufferedImage inputImage;

    public UnknownImage()   {
        super();
    }

    /**
     * Takes in an image from file. Creates a buffered image of specified dimensions. From image, a 2D array is created,
     * which is then turned into a 1D array for further processing
     * @param inputfile - file containing image
     * @param inputWidth - width of input file
     * @param inputHeight - height of input file
     * @param x - x coordinate for cropping image
     * @param y - y coordinate for cropping image
     * @param finalWidth - width of cropped image
     * @param finalHeight - height of cropped image
     * @return - 1D array of pixel values
     * @throws IOException
     */
    public static double[] unknownImageInput(String inputfile, int inputWidth, int inputHeight, int x, int y, int finalWidth, int finalHeight) throws IOException {
        BufferedImage bi = inputImage(inputfile, inputWidth, inputHeight);
        BufferedImage bi2 = cropImage(bi,x,y,finalWidth,finalHeight);
        double[][] d1 = createInputImageArray(bi2);
        ArrayProcessor ap = new ArrayProcessor();
        ap.flattenArray(d1);
        imageArray = ap.getFlatArray();
        return imageArray;
    }

    /**
     * Retrieves stored average pixel values from database and stores in 1D array
     *
     * @return outputArray - array of stored pixel values
     * Not used in Drivers but included as part of valid alternative persistent storage option
     */
    public static double[] retrieveAvgValues() throws SQLException {
        ArrayList<Double> d3 = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("SELECT avg_pv FROM avg_values ORDER BY p_ID ASC");
        ResultSet r1 = ps.executeQuery();
        while (r1.next()) {
            double p = r1.getDouble(1);
            d3.add(p);
        }
        double[] outputArray = new double[d3.size()];
        for (int i = 0; i < outputArray.length; i++) {
            outputArray[i] = d3.get(i);
        }
        return outputArray;
    }

    /**
     * Subtracts average values (in 1 array) from pixel values (in the other)
     * @param pixels - array of pixel values
     * @param avgValues - array of average values
     * @return outputArray - returned array of normalised pixel values
     */
    public static double[] applyAvgToUnkown(double[] pixels, double[] avgValues) {
        double [] outputArray = new double [pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            outputArray[i] = pixels[i] - avgValues[i];
        }
        return outputArray;
    }

    /**
     * Retrieves the 4 x principle component (per pixel) values from the database. These are processed and then placed
     * in a matrix (1 column per vector) which is returned.
     *
     * @return outputMatrix - matrix containing principle component values
     * Not used in Drivers but included as part of valid alternative persistent storage option
     */
    public static Array2DRowRealMatrix retrieveVectorsFromDB() throws SQLException, NotStrictlyPositiveException  {
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
        Array2DRowRealMatrix outputMatrix = new Array2DRowRealMatrix(d1.size(),4);
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
        outputMatrix.setColumn(0, d1a);
        outputMatrix.setColumn(1, d2a);
        outputMatrix.setColumn(2, d3a);
        outputMatrix.setColumn(3, d4a);
        return outputMatrix;
    }

    /**
     * Takes an array with the sum of each vector column contained in the final row. This final row is moved into a new
     * single row array for matching with stored weights from the database.
     * @param inputArray - array of vectors
     * @return weightsTable - array of weights
     */
    public static double [] createUnknownWeightsTable(double [][] inputArray)  {
        double [] weightsTable = new double[4];
        int a = inputArray.length-1;
        weightsTable[0] = inputArray[a][0];
        weightsTable[1] = inputArray[a][1];
        weightsTable[2] = inputArray[a][2];
        weightsTable[3] = inputArray[a][3];
        return weightsTable;
    }

    /**
     * Takes parameter of imageid associated with matching image. Checks if this matches imageid returned by the
     * matching process. Message outputted dependent on success of match
     * @param imageID - imageid of matching image
     * @param matchResult - imageid returned by matching process
     */
    public void matchDecision(int imageID, int matchResult) {
        System.out.println("Input Subject: User_"+imageID);
        System.out.println("Match Found: User_"+matchResult);
        if (imageID == matchResult)   {
            System.out.println("Successful Match");
        } else {
            System.out.println("No Match");
        }
    }

    /**
     * Creates a blank matrix of defined dimensions for storing vector data accessed from the database
     * @return weightsMatrix
     */
    public static Array2DRowRealMatrix createPCMatrix(int pixels)    {
        weightsMatrix = new Array2DRowRealMatrix(pixels,4);
        return weightsMatrix;
    }

    /**
     * Accessor for matrix containing vectors
     * @return weightsMatrix
     */
    public static Array2DRowRealMatrix getWeightsMatrix()  {
        return weightsMatrix;
    }
}
