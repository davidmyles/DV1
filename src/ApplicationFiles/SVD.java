package ApplicationFiles;


import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

/**
 * Creates SVD objects from a RealMatrix
 */
public class SVD {

    private static SingularValueDecomposition imageSVD;
    private static SingularValueDecomposition trainingSVD;
    //Array2DRowRealMatrix imageArray;

    /**
     * Performs SVD on RealMatrix for input image
     */
    public void CreateImageSVD(Array2DRowRealMatrix imageArray)	{
        imageSVD = new SingularValueDecomposition(imageArray);
    }

    /**
     * Performs SVD on RealMatrix for training image
     * @param trainingArray
     */
    public void CreateTrainingSVD(Array2DRowRealMatrix trainingArray) {
        trainingSVD = new SingularValueDecomposition(trainingArray);
    }

    /**
     * Accessor for input SVD object
     */
    public static SingularValueDecomposition getImageSVD()	{
        return imageSVD;
    }

    /**
     * Accessor for training SVD object
     * @return
     */
    public static org.apache.commons.math3.linear.SingularValueDecomposition getTrainingSVD()   {
        return trainingSVD;
    }
}
