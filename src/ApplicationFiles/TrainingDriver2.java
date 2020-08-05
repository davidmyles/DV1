package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class TrainingDriver2 {
    static double [] d2;
    static ImageMatrix im;
    static Array2DRowRealMatrix baseMatrix;

    public static void runTrainingDriver2a(int pixels, int imagesplus1) {
        im = new ImageMatrix();
        im.CreateTrainingRealMatrix(pixels, imagesplus1);
        baseMatrix = im.getTrainingMatrix();
    }

    public static void runTrainingDriver2b(String filepath, Array2DRowRealMatrix baseMatrix, int column)  {
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
        im.PopulateTrainingMatrix(baseMatrix, d2, column);
    }

    public static void main(String args[]) throws SQLException {
        runTrainingDriver2a(2500,10);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_01.png", baseMatrix, 0);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_02.png", baseMatrix, 1);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_03.png", baseMatrix, 2);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_04.png", baseMatrix, 3);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_05.png", baseMatrix, 4);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_06.png", baseMatrix, 5);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_07.png", baseMatrix, 6);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_08.png", baseMatrix, 7);
        runTrainingDriver2b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages3/img_09.png", baseMatrix, 8);
        ArrayProcessor ap = new ArrayProcessor();
        LinkSQL ls = new LinkSQL();
        ls.clearAvgValues();
        ls.clearPrincipleComponentsX2();
        ls.clearUnknownImageWeights();
        ls.clearWeights();
        ap.getAvgValues(baseMatrix,9);
        double[][] dx = ap.getArrayFromMatrix(baseMatrix);
        double [] dz = ap.getAvgValuesArray(dx);
        //System.out.println("Avg values list: " + Arrays.toString(dz));
        ls.savePixelAveragesToDB(dz);
        //System.out.println("Avg values added: " + Arrays.deepToString(baseMatrix.getData()));
        ap.applyAvgValues(baseMatrix);
        //System.out.println("Avg values applied: " + Arrays.deepToString(baseMatrix.getData()));
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
        //System.out.println("Raw Principle Components: " + Arrays.deepToString(r1.getData()));
        ap.createPCMatrix(2500,36);
        ap.calculateImageWeights(d1,r1,0, 0, 0);
        ap.calculateImageWeights(d1,r1,0, 1, 1);
        ap.calculateImageWeights(d1,r1,0, 2, 2);
        ap.calculateImageWeights(d1,r1,0, 3, 3);
        ap.calculateImageWeights(d1,r1,1, 0, 4);
        ap.calculateImageWeights(d1,r1,1, 1, 5);
        ap.calculateImageWeights(d1,r1,1, 2, 6);
        ap.calculateImageWeights(d1,r1,1, 3, 7);
        ap.calculateImageWeights(d1,r1,2, 0, 8);
        ap.calculateImageWeights(d1,r1,2, 1, 9);
        ap.calculateImageWeights(d1,r1,2, 2, 10);
        ap.calculateImageWeights(d1,r1,2, 3, 11);
        ap.calculateImageWeights(d1,r1,3, 0, 12);
        ap.calculateImageWeights(d1,r1,3, 1, 13);
        ap.calculateImageWeights(d1,r1,3, 2, 14);
        ap.calculateImageWeights(d1,r1,3, 3, 15);
        ap.calculateImageWeights(d1,r1,4, 0, 16);
        ap.calculateImageWeights(d1,r1,4, 1, 17);
        ap.calculateImageWeights(d1,r1,4, 2, 18);
        ap.calculateImageWeights(d1,r1,4, 3, 19);
        ap.calculateImageWeights(d1,r1,5, 0, 20);
        ap.calculateImageWeights(d1,r1,5, 1, 21);
        ap.calculateImageWeights(d1,r1,5, 2, 22);
        ap.calculateImageWeights(d1,r1,5, 3, 23);
        ap.calculateImageWeights(d1,r1,6, 0, 24);
        ap.calculateImageWeights(d1,r1,6, 1, 25);
        ap.calculateImageWeights(d1,r1,6, 2, 26);
        ap.calculateImageWeights(d1,r1,6, 3, 27);
        ap.calculateImageWeights(d1,r1,7, 0, 28);
        ap.calculateImageWeights(d1,r1,7, 1, 29);
        ap.calculateImageWeights(d1,r1,7, 2, 30);
        ap.calculateImageWeights(d1,r1,7, 3, 31);
        ap.calculateImageWeights(d1,r1,8, 0, 32);
        ap.calculateImageWeights(d1,r1,8, 1, 33);
        ap.calculateImageWeights(d1,r1,8, 2, 34);
        ap.calculateImageWeights(d1,r1,8, 3, 35);
        RealMatrix r2 = ap.getPCMatrix();
        //System.out.println("Calculated Principle Components: " + Arrays.deepToString(r2.getData()));
        double[][] d5 = ap.createWeightsArray(r2);
        //System.out.println("Weights Array: " + Arrays.deepToString(d5));
        ap.createWeightsTable(d5,4);
        double [][] d6 = ap.getWeightsTable();
        //System.out.println("Weights Table: " + Arrays.deepToString(d6));
        ls.saveWeightsTableToDB(d6);
    }
}
