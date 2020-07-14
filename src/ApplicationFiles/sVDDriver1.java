package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Driver class for test image SVD process
 */
public class sVDDriver1 {

    /**
     * Creates array & image. Saves image and uses array to create RealMatrix, from which singular values for that
     * array/image are generated
     */
    public static void runImageSVD() throws SQLException, ClassNotFoundException {
        RandomImageCreator ric = new RandomImageCreator();
        ric.createArray();
        double[][] d1 = ric.getPixelArray();
        ric.createImage(d1);
        File file = new File("/Users/davidjmyles/IdeaProjects/DV1/testimages/img_111.png");
        try {
            ImageIO.write(ric.newImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateRealMatrix crm = new CreateRealMatrix();
        crm.CreateImageRealMatrix(d1);
        Array2DRowRealMatrix a2rrm = crm.getImageMatrix();
        CreateSVD cs = new CreateSVD();
        cs.CreateImageSVD(a2rrm);
        SingularValueDecomposition svd = cs.getImageSVD();
        double[] d2 = svd.getSingularValues();
        System.out.println(Arrays.deepToString(new double[][]{d2}));
        System.out.println(Arrays.deepToString(d1));

    }

    public static void main(String args[]) throws IOException, SQLException, ClassNotFoundException {
        runImageSVD();
    }

}
