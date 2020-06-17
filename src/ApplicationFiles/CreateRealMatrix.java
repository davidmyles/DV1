package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 * Creates RealMatrix objects from an Array
 */
public class CreateRealMatrix {

    double[][] inputArray;
    Array2DRowRealMatrix imageMatrix;

    /**
     *Takes an array as a parameter and creates a RealMatrix
     */
    public CreateRealMatrix(double[][] inputArray) {
        imageMatrix = new Array2DRowRealMatrix(inputArray);

        }

    /**
     * Accessor for RealMatrix object
     */
    public Array2DRowRealMatrix getRealMatrix() {

        return imageMatrix;
    }
}



