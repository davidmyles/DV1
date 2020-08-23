package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Driver class which runs the training set for iris matching system
 */
public class IrisImageTrainer {

    static ImageMatrix imageMatrix;
    static Array2DRowRealMatrix baseMatrix;
    private static int inputWidth = 768;
    private static int inputHeight = 576;
    private static int x = 189;
    private static int y = 163;
    private static int finalWidth = 91;
    private static int finalHeight = 269;
    private static int finalPixels = finalWidth*finalHeight;
    private static int noOfImages = 60;

    /**
     * First driver component. Creates a blank matrix with rows matching the total pixel count from input images
     */
    public static void runIrisImageTrainer1() throws SQLException {
        imageMatrix = new ImageMatrix();
        imageMatrix.createTrainingRealMatrix(finalPixels, (noOfImages+1));
        baseMatrix = imageMatrix.getTrainingMatrix();
        User u = new User();
        u.deleteAllUsersFromDB();
    }

    /**
     * Second training driver component, and is run for each image. Image is processed, turned into 2D, the 1D array.
     * Array set as column in the matrix created in first component
     * @param filepath - file storage location of image
     * @param baseMatrix - matrix created in first component
     * @param column - column number to which matrix is added
     * @throws IOException
     */
    public static void runIrisImageTrainer2(String filepath, Array2DRowRealMatrix baseMatrix, int column) throws IOException, SQLException {
        Image i = new Image();
        int a = column+1;
        User u = new User("user_"+a, "fname"+a, "lname"+a, a);
        u.addUserToDB();
        BufferedImage bi = i.inputImage(filepath, inputWidth, inputHeight);
        BufferedImage bi2 = i.cropImage(bi,x,y ,finalWidth,finalHeight);
        ArrayProcessor ap = new ArrayProcessor();
        ImageMatrix im = new ImageMatrix();
        double [][] d1 = i.createInputImageArray(bi2);
        ap.flattenArray(d1);
        double [] d2 = ap.getFlatArray();
        im.populateTrainingMatrix(baseMatrix,d2,column);
    }

    /**
     * Third and final component in training set. Matrix with image arrays is processed to generate average values and
     * vectors. These are save to file and applied to training set. Result is a table of 4 weights for each image, which
     * are saved to database for matching.
     * @throws SQLException
     * @throws IOException
     */
    public static void runIrisImageTrainer3() throws SQLException, IOException {
        ArrayProcessor ap = new ArrayProcessor();
        LinkSQL ls = new LinkSQL();
        FileManager fm = new FileManager();
        ls.clearWeights();
        ap.getAvgValues(baseMatrix,noOfImages);
        double[] dx = ap.getTrainingAvgValues(baseMatrix);
        fm.saveAvgValuesToFile(dx, "saveData/AvgValues");
        ap.applyAvgValues(baseMatrix);
        System.out.println("Stage 7 Completed");
        double [][] d1 = ap.finaliseAvgValues(baseMatrix);
        imageMatrix.createImageRealMatrix(d1);
        Array2DRowRealMatrix a2rrm = imageMatrix.getTrainingMatrix();
        SingularValueDecomposition svd1 = new SingularValueDecomposition(a2rrm);
        RealMatrix r1 = svd1.getU();
        double [][] dr = fm.reduceVectors(r1);
        fm.saveVectorsToFile(dr, "saveData/Vectors");
        System.out.println("Stage 8 Completed");
        ap.createPCMatrix(finalPixels,(noOfImages*4));
        ap.applyTrainingVectors(d1,r1,1,0);
        ap.applyTrainingVectors(d1,r1,2,4);
        ap.applyTrainingVectors(d1,r1,3,8);
        ap.applyTrainingVectors(d1,r1,4,12);
        ap.applyTrainingVectors(d1,r1,5,16);
        ap.applyTrainingVectors(d1,r1,6,20);
        ap.applyTrainingVectors(d1,r1,7,24);
        ap.applyTrainingVectors(d1,r1,8,28);
        ap.applyTrainingVectors(d1,r1,9,32);
        ap.applyTrainingVectors(d1,r1,10,36);
        ap.applyTrainingVectors(d1,r1,11,40);
        ap.applyTrainingVectors(d1,r1,12,44);
        ap.applyTrainingVectors(d1,r1,13,48);
        ap.applyTrainingVectors(d1,r1,14,52);
        ap.applyTrainingVectors(d1,r1,15,56);
        ap.applyTrainingVectors(d1,r1,16,60);
        ap.applyTrainingVectors(d1,r1,17,64);
        ap.applyTrainingVectors(d1,r1,18,68);
        ap.applyTrainingVectors(d1,r1,19,72);
        ap.applyTrainingVectors(d1,r1,20,76);
        ap.applyTrainingVectors(d1,r1,21,80);
        ap.applyTrainingVectors(d1,r1,22,84);
        ap.applyTrainingVectors(d1,r1,23,88);
        ap.applyTrainingVectors(d1,r1,24,92);
        ap.applyTrainingVectors(d1,r1,25,96);
        ap.applyTrainingVectors(d1,r1,26,100);
        ap.applyTrainingVectors(d1,r1,27,104);
        ap.applyTrainingVectors(d1,r1,28,108);
        ap.applyTrainingVectors(d1,r1,29,112);
        ap.applyTrainingVectors(d1,r1,30,116);
        ap.applyTrainingVectors(d1,r1,31,120);
        ap.applyTrainingVectors(d1,r1,32,124);
        ap.applyTrainingVectors(d1,r1,33,128);
        ap.applyTrainingVectors(d1,r1,34,132);
        ap.applyTrainingVectors(d1,r1,35,136);
        ap.applyTrainingVectors(d1,r1,36,140);
        ap.applyTrainingVectors(d1,r1,37,144);
        ap.applyTrainingVectors(d1,r1,38,148);
        ap.applyTrainingVectors(d1,r1,39,152);
        ap.applyTrainingVectors(d1,r1,40,156);
        ap.applyTrainingVectors(d1,r1,41,160);
        ap.applyTrainingVectors(d1,r1,42,164);
        ap.applyTrainingVectors(d1,r1,43,168);
        ap.applyTrainingVectors(d1,r1,44,172);
        ap.applyTrainingVectors(d1,r1,45,176);
        ap.applyTrainingVectors(d1,r1,46,180);
        ap.applyTrainingVectors(d1,r1,47,184);
        ap.applyTrainingVectors(d1,r1,48,188);
        ap.applyTrainingVectors(d1,r1,49,192);
        ap.applyTrainingVectors(d1,r1,50,196);
        ap.applyTrainingVectors(d1,r1,51,200);
        ap.applyTrainingVectors(d1,r1,52,204);
        ap.applyTrainingVectors(d1,r1,53,208);
        ap.applyTrainingVectors(d1,r1,54,212);
        ap.applyTrainingVectors(d1,r1,55,216);
        ap.applyTrainingVectors(d1,r1,56,220);
        ap.applyTrainingVectors(d1,r1,57,224);
        ap.applyTrainingVectors(d1,r1,58,228);
        ap.applyTrainingVectors(d1,r1,59,232);
        ap.applyTrainingVectors(d1,r1,60,236);
        System.out.println("Stage 11 Completed");
        RealMatrix r2 = ap.getPCMatrix();
        double[][] d5 = ap.createWeightsArray(r2);
        ap.createWeightsTable(d5,4);
        double [][] d6 = ap.getWeightsTable();
        System.out.println("Weights: " + Arrays.deepToString(d6));
        ls.saveWeightsTableToDB(d6);
    }

    /**
     * Main method which runs the 3 components of the training driver
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String args[]) throws SQLException, IOException {
        runIrisImageTrainer1();
        runIrisImageTrainer2("IrisImages(576x768)/001L_1.png", baseMatrix, 0);
        runIrisImageTrainer2("IrisImages(576x768)/002L_1.png", baseMatrix, 1);
        runIrisImageTrainer2("IrisImages(576x768)/003L_1.png", baseMatrix, 2);
        runIrisImageTrainer2("IrisImages(576x768)/004L_1.png", baseMatrix, 3);
        runIrisImageTrainer2("IrisImages(576x768)/005L_1.png", baseMatrix, 4);
        runIrisImageTrainer2("IrisImages(576x768)/006L_1.png", baseMatrix, 5);
        runIrisImageTrainer2("IrisImages(576x768)/007L_1.png", baseMatrix, 6);
        runIrisImageTrainer2("IrisImages(576x768)/008L_1.png", baseMatrix, 7);
        runIrisImageTrainer2("IrisImages(576x768)/009L_1.png", baseMatrix, 8);
        runIrisImageTrainer2("IrisImages(576x768)/010L_1.png", baseMatrix, 9);
        System.out.println("Stage 1 Completed");
        runIrisImageTrainer2("IrisImages(576x768)/011L_1.png", baseMatrix, 10);
        runIrisImageTrainer2("IrisImages(576x768)/012L_1.png", baseMatrix, 11);
        runIrisImageTrainer2("IrisImages(576x768)/013L_1.png", baseMatrix, 12);
        runIrisImageTrainer2("IrisImages(576x768)/014L_1.png", baseMatrix, 13);
        runIrisImageTrainer2("IrisImages(576x768)/015L_1.png", baseMatrix, 14);
        runIrisImageTrainer2("IrisImages(576x768)/016L_1.png", baseMatrix, 15);
        runIrisImageTrainer2("IrisImages(576x768)/017L_1.png", baseMatrix, 16);
        runIrisImageTrainer2("IrisImages(576x768)/018L_1.png", baseMatrix, 17);
        runIrisImageTrainer2("IrisImages(576x768)/019L_1.png", baseMatrix, 18);
        runIrisImageTrainer2("IrisImages(576x768)/020L_1.png", baseMatrix, 19);
        System.out.println("Stage 2 Completed");
        runIrisImageTrainer2("IrisImages(576x768)/021L_1.png", baseMatrix, 20);
        runIrisImageTrainer2("IrisImages(576x768)/022L_1.png", baseMatrix, 21);
        runIrisImageTrainer2("IrisImages(576x768)/023L_1.png", baseMatrix, 22);
        runIrisImageTrainer2("IrisImages(576x768)/024L_1.png", baseMatrix, 23);
        runIrisImageTrainer2("IrisImages(576x768)/025L_1.png", baseMatrix, 24);
        runIrisImageTrainer2("IrisImages(576x768)/026L_1.png", baseMatrix, 25);
        runIrisImageTrainer2("IrisImages(576x768)/027L_1.png", baseMatrix, 26);
        runIrisImageTrainer2("IrisImages(576x768)/028L_1.png", baseMatrix, 27);
        runIrisImageTrainer2("IrisImages(576x768)/029L_1.png", baseMatrix, 28);
        runIrisImageTrainer2("IrisImages(576x768)/030L_1.png", baseMatrix, 29);
        System.out.println("Stage 3 Completed");
        runIrisImageTrainer2("IrisImages(576x768)/031L_1.png", baseMatrix, 30);
        runIrisImageTrainer2("IrisImages(576x768)/032L_1.png", baseMatrix, 31);
        runIrisImageTrainer2("IrisImages(576x768)/033L_1.png", baseMatrix, 32);
        runIrisImageTrainer2("IrisImages(576x768)/034L_1.png", baseMatrix, 33);
        runIrisImageTrainer2("IrisImages(576x768)/035L_1.png", baseMatrix, 34);
        runIrisImageTrainer2("IrisImages(576x768)/036L_1.png", baseMatrix, 35);
        runIrisImageTrainer2("IrisImages(576x768)/037L_1.png", baseMatrix, 36);
        runIrisImageTrainer2("IrisImages(576x768)/038L_1.png", baseMatrix, 37);
        runIrisImageTrainer2("IrisImages(576x768)/039L_1.png", baseMatrix, 38);
        runIrisImageTrainer2("IrisImages(576x768)/040L_1.png", baseMatrix, 39);
        System.out.println("Stage 4 Completed");
        runIrisImageTrainer2("IrisImages(576x768)/041L_1.png", baseMatrix, 40);
        runIrisImageTrainer2("IrisImages(576x768)/042L_1.png", baseMatrix, 41);
        runIrisImageTrainer2("IrisImages(576x768)/043L_1.png", baseMatrix, 42);
        runIrisImageTrainer2("IrisImages(576x768)/044L_1.png", baseMatrix, 43);
        runIrisImageTrainer2("IrisImages(576x768)/045L_1.png", baseMatrix, 44);
        runIrisImageTrainer2("IrisImages(576x768)/046L_1.png", baseMatrix, 45);
        runIrisImageTrainer2("IrisImages(576x768)/047L_1.png", baseMatrix, 46);
        runIrisImageTrainer2("IrisImages(576x768)/048L_1.png", baseMatrix, 47);
        runIrisImageTrainer2("IrisImages(576x768)/049L_1.png", baseMatrix, 48);
        runIrisImageTrainer2("IrisImages(576x768)/050L_1.png", baseMatrix, 49);
        System.out.println("Stage 5 Completed");
        runIrisImageTrainer2("IrisImages(576x768)/051L_1.png", baseMatrix, 50);
        runIrisImageTrainer2("IrisImages(576x768)/052L_1.png", baseMatrix, 51);
        runIrisImageTrainer2("IrisImages(576x768)/053L_1.png", baseMatrix, 52);
        runIrisImageTrainer2("IrisImages(576x768)/054L_1.png", baseMatrix, 53);
        runIrisImageTrainer2("IrisImages(576x768)/055L_1.png", baseMatrix, 54);
        runIrisImageTrainer2("IrisImages(576x768)/056L_1.png", baseMatrix, 55);
        runIrisImageTrainer2("IrisImages(576x768)/057L_1.png", baseMatrix, 56);
        runIrisImageTrainer2("IrisImages(576x768)/058L_1.png", baseMatrix, 57);
        runIrisImageTrainer2("IrisImages(576x768)/059L_1.png", baseMatrix, 58);
        runIrisImageTrainer2("IrisImages(576x768)/060L_1.png", baseMatrix, 59);
        System.out.println("Stage 6 Completed");
        runIrisImageTrainer3();
    }
}
