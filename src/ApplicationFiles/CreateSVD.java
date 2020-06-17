package ApplicationFiles;


import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

/**
 * Creates SVD objects from a RealMatrix
 */
public class CreateSVD {

    Array2DRowRealMatrix imageArray;
    private SingularValueDecomposition svd;

    /**
     * Constructor for SVD, takes RealMatrix object as parameter
     */
    public CreateSVD(Array2DRowRealMatrix imageArray)	{
        svd = new SingularValueDecomposition(imageArray);
    }

    /**
     * Accessor for SVD object
     */
    public SingularValueDecomposition getSVD()	{
        return svd;

    }
}
