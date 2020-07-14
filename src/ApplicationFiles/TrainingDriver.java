package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class TrainingDriver {

    private static CreateRealMatrix crm = new CreateRealMatrix();

    public static void createTrainingMatrix() {
        crm.CreateTrainingRealMatrix(25, 25);
    }

    public static void runTrainingSVD(String filepath, int row) {
        crm.getTrainingMatrix();
        RandomImageCreator ric1 = new RandomImageCreator();
        ric1.createArray();
        double [][] d1 = ric1.getPixelArray();
        ric1.createImage(d1);
        //double[][] d1 = ric1.getPixelArray();
        ric1.flattenArray(d1);
        double[] fa1 = ric1.getFlatArray();
        crm.addImageDataset(row, fa1);
        File file1 = new File(filepath);
        try {
            ImageIO.write(ric1.newImage, "png", file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void completeTrainingSVD () throws SQLException, ClassNotFoundException {
        CreateSVD cs = new CreateSVD();
        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        cs.CreateTrainingSVD(a2rrm);
        SingularValueDecomposition svd = cs.getTrainingSVD();
        double[] d1 = svd.getSingularValues();
        StoreSingularValues ssv1 = new StoreSingularValues();
        ssv1.insertValues("SVD_1", cs);
        System.out.println(Arrays.deepToString(new double[][]{d1}));
    }

    public static void main (String args[]) throws IOException, SQLException, ClassNotFoundException {
        createTrainingMatrix();
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_01.png", 0);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_02.png", 1);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_03.png", 2);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_04.png", 3);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_05.png", 4);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_06.png", 5);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_07.png", 6);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_08.png", 7);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_09.png", 8);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_10.png", 9);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_11.png", 10);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_12.png", 11);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_13.png", 12);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_14.png", 13);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_15.png", 14);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_16.png", 15);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_17.png", 16);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_18.png", 17);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_19.png", 18);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_20.png", 19);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_21.png", 20);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_22.png", 21);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_23.png", 22);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_24.png", 23);
        runTrainingSVD("/Users/davidjmyles/IdeaProjects/DV1/trainingimages/img_25.png", 24);
        completeTrainingSVD();

        Array2DRowRealMatrix a2rrm = crm.getTrainingMatrix();
        double[][] dx = a2rrm.getData();
        System.out.println(Arrays.deepToString(dx));
    }

}
