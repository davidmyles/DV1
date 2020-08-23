package ApplicationFiles;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Driver class for processing unknown image and completing matching process
 */
public class ImageMatchDriver {

    private static int inputWidth = 768;
    private static int inputHeight = 576;
    private static int x = 189;
    private static int y = 163;
    private static int finalWidth = 91;
    private static int finalHeight = 269;
    private static String avgValuesFilepath = "saveData/AvgValues";
    private static String vectorsFilepath = "saveData/Vectors";

    /**
     * Driver method for matching unknown image. Image is normalised and pixel values multiplied by training vectors.
     * The resulting weights are compared with stored image weights to generate a match
     */
    public static void runUnknownImageMatch(String imageFilepath, String userID) throws SQLException, IOException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        UnknownImage ui = new UnknownImage();
        LinkSQL ls = new LinkSQL();
        FileManager fm = new FileManager();
        ArrayProcessor ap = new ArrayProcessor();
        double[] p1 = ui.unknownImageInput(imageFilepath,inputWidth,inputHeight,x,y,finalWidth,finalHeight);
        int pixelCount = p1.length;
        double[] p2 = fm.retrieveAvgValuesFromFile(avgValuesFilepath, (pixelCount));
        double[] p3 = ui.applyAvgToUnkown(p1, p2);
        double[][] p4 = fm.retrieveVectorsFromFile(vectorsFilepath, (pixelCount));
        Array2DRowRealMatrix rm = new Array2DRowRealMatrix(p4);
        ap.createPCMatrix(pixelCount,4);
        Array2DRowRealMatrix rm2 = ap.getPCMatrix();
        ap.applyTestVectors(p3,rm);
        double[][] p5 = ap.createWeightsArray(rm2);
        double[] p6 = ui.createUnknownWeightsTable(p5);
        int matchResult = ls.matchImage(p6,4);
        int imageID = ls.retrieveUserImageID(userID);
        ui.matchDecision(imageID, matchResult);
        stopwatch.stop();
        long matchTime = stopwatch.getTime();
        System.out.println("Match Time: " + matchTime);
        System.out.println("============================================================");
    }

    public static void main(String args[]) throws SQLException, IOException {
        runUnknownImageMatch("IrisImages(576x768)/001L_2.png", "user_1");
        runUnknownImageMatch("IrisImages(576x768)/002L_2.png", "user_2");
        runUnknownImageMatch("IrisImages(576x768)/003L_2.png", "user_3");
        runUnknownImageMatch("IrisImages(576x768)/004L_2.png", "user_4");
        runUnknownImageMatch("IrisImages(576x768)/005L_2.png", "user_5");
        runUnknownImageMatch("IrisImages(576x768)/006L_2.png", "user_6");
        runUnknownImageMatch("IrisImages(576x768)/007L_2.png", "user_7");
        runUnknownImageMatch("IrisImages(576x768)/008L_2.png", "user_8");
        runUnknownImageMatch("IrisImages(576x768)/009L_2.png", "user_9");
        runUnknownImageMatch("IrisImages(576x768)/010L_2.png", "user_10");
        runUnknownImageMatch("IrisImages(576x768)/011L_2.png", "user_11");
        runUnknownImageMatch("IrisImages(576x768)/012L_2.png", "user_12");
        runUnknownImageMatch("IrisImages(576x768)/013L_2.png", "user_13");
        runUnknownImageMatch("IrisImages(576x768)/014L_2.png", "user_14");
        runUnknownImageMatch("IrisImages(576x768)/015L_2.png", "user_15");
        runUnknownImageMatch("IrisImages(576x768)/016L_2.png", "user_16");
        runUnknownImageMatch("IrisImages(576x768)/017L_2.png", "user_17");
        runUnknownImageMatch("IrisImages(576x768)/018L_2.png", "user_18");
        runUnknownImageMatch("IrisImages(576x768)/019L_2.png", "user_19");
        runUnknownImageMatch("IrisImages(576x768)/020L_2.png", "user_20");
        runUnknownImageMatch("IrisImages(576x768)/021L_2.png", "user_21");
        runUnknownImageMatch("IrisImages(576x768)/022L_2.png", "user_22");
        runUnknownImageMatch("IrisImages(576x768)/023L_2.png", "user_23");
        runUnknownImageMatch("IrisImages(576x768)/024L_2.png", "user_24");
        runUnknownImageMatch("IrisImages(576x768)/025L_2.png", "user_25");
        runUnknownImageMatch("IrisImages(576x768)/026L_2.png", "user_26");
        runUnknownImageMatch("IrisImages(576x768)/027L_2.png", "user_27");
        runUnknownImageMatch("IrisImages(576x768)/028L_2.png", "user_28");
        runUnknownImageMatch("IrisImages(576x768)/029L_2.png", "user_29");
        runUnknownImageMatch("IrisImages(576x768)/030L_2.png", "user_30");
        runUnknownImageMatch("IrisImages(576x768)/031L_2.png", "user_31");
        runUnknownImageMatch("IrisImages(576x768)/032L_2.png", "user_32");
        runUnknownImageMatch("IrisImages(576x768)/033L_2.png", "user_33");
        runUnknownImageMatch("IrisImages(576x768)/034L_2.png", "user_34");
        runUnknownImageMatch("IrisImages(576x768)/035L_2.png", "user_35");
        runUnknownImageMatch("IrisImages(576x768)/036L_2.png", "user_36");
        runUnknownImageMatch("IrisImages(576x768)/037L_2.png", "user_37");
        runUnknownImageMatch("IrisImages(576x768)/038L_2.png", "user_38");
        runUnknownImageMatch("IrisImages(576x768)/039L_2.png", "user_39");
        runUnknownImageMatch("IrisImages(576x768)/040L_2.png", "user_40");
        runUnknownImageMatch("IrisImages(576x768)/041L_2.png", "user_41");
        runUnknownImageMatch("IrisImages(576x768)/042L_2.png", "user_42");
        runUnknownImageMatch("IrisImages(576x768)/043L_2.png", "user_43");
        runUnknownImageMatch("IrisImages(576x768)/044L_2.png", "user_44");
        runUnknownImageMatch("IrisImages(576x768)/045L_2.png", "user_45");
        runUnknownImageMatch("IrisImages(576x768)/046L_2.png", "user_46");
        runUnknownImageMatch("IrisImages(576x768)/047L_2.png", "user_47");
        runUnknownImageMatch("IrisImages(576x768)/048L_2.png", "user_48");
        runUnknownImageMatch("IrisImages(576x768)/049L_2.png", "user_49");
        runUnknownImageMatch("IrisImages(576x768)/050L_2.png", "user_50");
        runUnknownImageMatch("IrisImages(576x768)/051L_2.png", "user_51");
        runUnknownImageMatch("IrisImages(576x768)/052L_2.png", "user_52");
        runUnknownImageMatch("IrisImages(576x768)/053L_2.png", "user_53");
        runUnknownImageMatch("IrisImages(576x768)/054L_2.png", "user_54");
        runUnknownImageMatch("IrisImages(576x768)/055L_2.png", "user_55");
        runUnknownImageMatch("IrisImages(576x768)/056L_2.png", "user_56");
        runUnknownImageMatch("IrisImages(576x768)/057L_2.png", "user_57");
        runUnknownImageMatch("IrisImages(576x768)/058L_2.png", "user_58");
        runUnknownImageMatch("IrisImages(576x768)/059L_2.png", "user_59");
        runUnknownImageMatch("IrisImages(576x768)/060L_2.png", "user_60");
    }
}
