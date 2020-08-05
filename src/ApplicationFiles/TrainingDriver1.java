package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class TrainingDriver1 {

    static double [] d2;
    static ImageMatrix im;
    static Array2DRowRealMatrix baseMatrix;

    public static void runTrainingDriver1a()    {
        im = new ImageMatrix();
        im.CreateTrainingRealMatrix(9, 4);
        baseMatrix = im.getTrainingMatrix();

    }

    public static void runTrainingDriver1b(String filepath, Array2DRowRealMatrix baseMatrix, int row)  {
        RandomImage ri1 = new RandomImage();
        ri1.createArray();
        double [][] d1 = ri1.getPixelArray();
        ri1.flattenArray(d1);
        ri1.createImage(d1);
        d2 = ri1.getFlatArray();
        System.out.println(Arrays.toString(d2));
        File file1 = new File(filepath);
        try {
            ImageIO.write(ri1.newImage, "png", file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.PopulateTrainingMatrix(baseMatrix, d2, row);
    }




    public static void main(String args[]) throws SQLException {
        runTrainingDriver1a();
        runTrainingDriver1b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png", baseMatrix, 0);
        runTrainingDriver1b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_02.png", baseMatrix, 1);
        runTrainingDriver1b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_03.png", baseMatrix, 2);
        ArrayProcessor ap = new ArrayProcessor();
        LinkSQL ls = new LinkSQL();
        ap.getAvgValues(baseMatrix, 3);
        double[][] dx = ap.getArrayFromMatrix(baseMatrix);
        double [] dz = ap.getAvgValuesArray(dx);
        ls.savePixelAveragesToDB(dz);
        System.out.println(Arrays.deepToString(baseMatrix.getData()));
        ap.applyAvgValues(baseMatrix);
        System.out.println(Arrays.deepToString(baseMatrix.getData()));
        ap.finaliseAvgValues(baseMatrix);
        SVD svd = new SVD();
        double [][] d1 = ap.finaliseAvgValues(baseMatrix);
        System.out.println("D1: " + Arrays.deepToString(d1));
        im.CreateImageRealMatrix(d1);
        Array2DRowRealMatrix a2rrm = im.getTrainingMatrix();
        svd.CreateTrainingSVD(a2rrm);
        SingularValueDecomposition svd1 = svd.getTrainingSVD();
        RealMatrix r1 = svd1.getU();
        double[][] dc = ap.getArrayFromMatrix(r1);
        ls.savePrincipleComponentsToDB(dc);
        System.out.println("Raw Principle Components: " + Arrays.deepToString(r1.getData()));
        ap.createPCMatrix(9,6);
        ap.calculateImageWeights(d1,r1,0, 0, 0);
        ap.calculateImageWeights(d1,r1,0, 1, 1);
        ap.calculateImageWeights(d1,r1,1, 0, 2);
        ap.calculateImageWeights(d1,r1,1, 1, 3);
        ap.calculateImageWeights(d1,r1,2, 0, 4);
        ap.calculateImageWeights(d1,r1,2, 1, 5);
        RealMatrix r2 = ap.getPCMatrix();
        System.out.println("Calculated Principle Components: " + Arrays.deepToString(r2.getData()));
        double[][] d5 = ap.createWeightsArray(r2);
        System.out.println("Weights Array: " + Arrays.deepToString(d5));
        ap.createWeightsTable(d5,2);
        double [][] d6 = ap.getWeightsTable();
        System.out.println("Weights Table: " + Arrays.deepToString(d6));
        ls.saveWeightsTableToDBx2PC(d6);



    }
}
