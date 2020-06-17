package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Driver class for test image SVD process
 */
public class sVDDriver {

    /**
     * Creates array & image. Saves image and uses array to create RealMatrix, from which singular values for that
     * array/image are generated
     */
    public static void runTestSVD()   {
        RandomImageCreator ric = new RandomImageCreator();
        ric.createArray();
        ric.createImage();
        File file = new File("/Users/davidjmyles/IdeaProjects/DV1/testimages/testimage5.png");
        try {
            ImageIO.write(ric.newImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[][] d1 = ric.getPixelArray();
        CreateRealMatrix crm = new CreateRealMatrix(d1);
        Array2DRowRealMatrix a2rrm = crm.getRealMatrix();
        CreateSVD cs = new CreateSVD(a2rrm);
        SingularValueDecomposition svd = cs.getSVD();
        double[] d2 = svd.getSingularValues();
        System.out.println(Arrays.deepToString(new double[][]{d2                                                                                           }));

    }

    public static void main(String args[]) throws IOException {
        runTestSVD();
    }

}
