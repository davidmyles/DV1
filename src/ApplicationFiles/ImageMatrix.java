package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 * Class for creating ImageMatrix object for further processing
 */
public class ImageMatrix {

    Array2DRowRealMatrix imageMatrix;
    Array2DRowRealMatrix trainingMatrix;

    /**
     * Creates matrix with the dimensions and values of 2D array
     * @param imageInputArray - array to be turned into matrix
     */
    public void createImageRealMatrix(double[][] imageInputArray) {
        imageMatrix = new Array2DRowRealMatrix(imageInputArray);
    }

    /**
     * Creates a blank matrix of specified dimensions
     * @param row - number of rows in new matrix
     * @param column - number of columns in new matrix
     */
    public void createTrainingRealMatrix(int row, int column) {
        trainingMatrix = new Array2DRowRealMatrix(row, column);
    }

    /**
     * populates specified column of the matrix with array values
     * @param baseMatrix - matrix to be populated
     * @param inputArray - array to be added to matrix
     * @param column - column at which array to be added to matrix
     */
    public void populateTrainingMatrix(Array2DRowRealMatrix baseMatrix, double[] inputArray, int column) {
        baseMatrix.setColumn(column, inputArray);
    }

    /**
     * Accessor for matching image RealMatrix object
     * @return imageMatrix - matrix
     */
    public Array2DRowRealMatrix getImageMatrix () {
        return imageMatrix;
    }

    /**
     * Accessor for training image RealMatrix object
     * @return imageMatrix - matrix
     */
    public Array2DRowRealMatrix getTrainingMatrix () {
        return trainingMatrix;
    }
}




