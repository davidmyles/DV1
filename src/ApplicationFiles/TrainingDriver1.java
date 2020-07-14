package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TrainingDriver1 {

    static double [] d2;
    static CreateRealMatrix crm;
    static Array2DRowRealMatrix baseMatrix;

    public static void runTrainingDriver1a()    {
        crm = new CreateRealMatrix();
        crm.CreateTrainingRealMatrix(9, 4);
        baseMatrix = crm.getTrainingMatrix();

    }

    public static void runTrainingDriver1b(String filepath, Array2DRowRealMatrix baseMatrix, int row)  {
        RandomImageCreator ric1 = new RandomImageCreator();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ric1.flattenArray(d1);
        ric1.createImage(d1);
        d2 = ric1.getFlatArray();
        System.out.println(Arrays.toString(d2));
        File file1 = new File(filepath);
        try {
            ImageIO.write(ric1.newImage, "png", file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        crm.PopulateTrainingMatrix(baseMatrix, d2, row);
    }

    public static void main(String args[]) {
        runTrainingDriver1a();
        runTrainingDriver1b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png", baseMatrix, 0);
        runTrainingDriver1b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_02.png", baseMatrix, 1);
        runTrainingDriver1b("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_03.png", baseMatrix, 2);
        crm.getAvgValues(baseMatrix);
        System.out.println(Arrays.deepToString(baseMatrix.getData()));
        crm.applyAvgValues(baseMatrix);
        System.out.println(Arrays.deepToString(baseMatrix.getData()));
        crm.finaliseAvgValues(baseMatrix);
        CreateSVD csvd = new CreateSVD();
        double [][] d1 = crm.finaliseAvgValues(baseMatrix);
        crm.CreateImageRealMatrix(d1);
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        csvd.CreateTrainingSVD(a2rrm);
        SingularValueDecomposition svd = csvd.getTrainingSVD();
        //RealMatrix d2 = svd.getS();
        //double [] d3 = svd.getSingularValues();
        RealMatrix d4 = svd.getUT();
        crm.createImageWeightsArray(d1);
        double [][] normalisedArray = crm.createImageWeightsArray(d1);
        crm.calculateImageWeights(d1,normalisedArray,d4,0);
        double [][] d5 = crm.getImageWeightsArray();
        //RealMatrix d5 = svd.getU();
        //System.out.println(Arrays.deepToString(d2.getData()));
        System.out.println(Arrays.deepToString(d4.getData()));
        //System.out.println(Arrays.deepToString(d5.getData()));
        //System.out.println(Arrays.toString(d3));

    }
}
