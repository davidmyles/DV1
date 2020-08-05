package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 * Creates RealMatrix objects from an Array
 */
public class ImageMatrix {

    Array2DRowRealMatrix imageMatrix;
    Array2DRowRealMatrix trainingMatrix;
    Array2DRowRealMatrix pcMatrix;
    private double[][] weightsTable;


    /**
     * Takes an array as a parameter and creates a RealMatrix with the array values
     */
    public void CreateImageRealMatrix(double[][] imageInputArray) {
        imageMatrix = new Array2DRowRealMatrix(imageInputArray);
    }

    /**
     * Creates a blank RealMatrix
     */
    public void CreateTrainingRealMatrix(int row, int column) {
        trainingMatrix = new Array2DRowRealMatrix(row, column);
    }

    /**
     * Takes a blank RealMatrix and 1D array as parameters. Inserts the array values into the matrix at the specified row
     */
    public void populateTrainingMatrix(Array2DRowRealMatrix baseMatrix, double[] inputArray, int column) {
        baseMatrix.setColumn(column, inputArray);
    }

    /**
     * Takes a 1D array and uses to populate a parameter-specified row of a RealMatrix
     */
    public void addImageDataset ( int row, double[] trainingInputArray){
        trainingMatrix.setRow(row, trainingInputArray);
    }

    /**
     * Accessor for matching image RealMatrix object
     */
    public Array2DRowRealMatrix getImageMatrix () {
        return imageMatrix;
    }

    /**
     * Accessor for training image RealMatrix object
     */
    public Array2DRowRealMatrix getTrainingMatrix () {
        return trainingMatrix;
    }
}




