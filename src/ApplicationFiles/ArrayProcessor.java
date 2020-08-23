package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Class which contains methods for processing image array values
 */
public class ArrayProcessor {

    Array2DRowRealMatrix pcMatrix;
    private double[][] weightsTable;
    private double[] flatArray;

    /**
     * Parameter is a populated matrix. Average value for elements in each row are calculated, with a new column for
     * average row values added at the end of existing matrix
     * @param baseMatrix - matrix containing image pixel values
     * @param noOfImages - the number of image columns in the matrix
     * @return
     */
    public Array2DRowRealMatrix getAvgValues(Array2DRowRealMatrix baseMatrix, int noOfImages) {
        int a = baseMatrix.getColumnDimension() - 1;
        int b = baseMatrix.getRowDimension();
        for (int i = 0; i < b; i++) {
            double[] c = baseMatrix.getRow(i);
            int d = ((int) DoubleStream.of(c).sum() / noOfImages);
            baseMatrix.addToEntry(i, a, d);
        }
        return baseMatrix;
    }

    /**
     * Takes a 2D array and turns into 1D array with the same values
     * @param inputArray - a 2D array
     */
    public void flattenArray(double [][] inputArray)  {
        flatArray = Stream.of(inputArray).flatMapToDouble(DoubleStream::of).toArray();
    }

    /**
     * Takes the RealMatrix with average row values and transfers those values into a new 1D array for storage
     * @param inputMatrix - matrix with row averages in final column
     * @return outputArray - array of average values
     */
    public double [] getTrainingAvgValues(RealMatrix inputMatrix)  {
        double [] outputArray = inputMatrix.getColumn((inputMatrix.getColumnDimension()-1));
        return outputArray;
    }

    /**
     * Takes a matrix with average row values in final column. Subtracts this average from each element in its row
     * @param inputMatrix - matrix containing pixel & average values
     * @return inputMatrix - normalised matrix with average values applied
     */
    public Array2DRowRealMatrix applyAvgValues(Array2DRowRealMatrix inputMatrix) {
        int c = inputMatrix.getColumnDimension() - 1;
        double z = inputMatrix.getRowDimension();
        double x = inputMatrix.getColumnDimension();
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < x; j++) {
                double g = inputMatrix.getEntry(i, j);
                double h = inputMatrix.getEntry(i, c);
                double f = g - h;
                inputMatrix.setEntry(i, j, f);
            }
        }
        return inputMatrix;
    }

    /**
     * Creates a new array from the normalised matrix, with the average values column removed
     * @param inputMatrix - normalised matrix
     * @return outputArray - array with final column removed
     */
    public double[][] finaliseAvgValues(Array2DRowRealMatrix inputMatrix) {
        double[][] d1 = inputMatrix.getData();
        int a = d1.length;
        int b = d1[0].length - 1;
        double[][] outputArray = new double[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                outputArray[i][j] = d1[i][j];
            }
        }
        return outputArray;
    }

    /**
     * Creates a blank Matrix of specified dimensions
     * @param rows - number of rows in matrix
     * @param columns - number of columns in matrix
     * @return pcMatrix - blank matrix
     */
    public Array2DRowRealMatrix createPCMatrix(int rows, int columns)    {
        pcMatrix = new Array2DRowRealMatrix(rows,columns);
        return pcMatrix;
    }

    /**
     * Takes a normalised pixel array and matrix of principle components. Each image value is multiplied by its
     * corresponding pixel value from the 1st column of principle components. This is repeated for the 2nd,3rd &
     * 4th principle component columns, resulting in 4 columns (vectors) for the image.
     * @param array - normalised array of image pixel values
     * @param matrix - matrix of principle components, returned from SVD function
     * @param image - image to be processed
     * @param counter - indicates which column the processed array is added to
     */
    public void applyTrainingVectors(double [][] array, RealMatrix matrix, int image, int counter) {
        double[] columnArray = new double[array.length];
        double[][] matrixArray = matrix.getData();
            for (int i = 0; i < array.length; i++) {
                columnArray[i] = array[i][image - 1];
            }
        double [] newArray = new double[columnArray.length];
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < columnArray.length; k++) {
                    newArray[k] = columnArray[k] * matrixArray[k][j];
                }
                pcMatrix.setColumn(j+counter, newArray);
            }
    }

    /**
     * Takes a normalised pixel array and matrix of principle components. Each image value is multiplied by its
     * corresponding pixel value from the 1st column of principle components. This is repeated for the 2nd,3rd &
     * 4th principle component columns, resulting in 4 columns (vectors) for the image.
     * @param array - normalised array of image pixel values
     * @param matrix - matrix of principle components, returned from SVD function
     */
    public void applyTestVectors(double [] array, RealMatrix matrix) {
        double[][] matrixArray = matrix.getData();
        double [] newArray = new double[array.length];
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < array.length; k++) {
                newArray[k] = array[k] * matrixArray[k][j];
            }
            pcMatrix.setColumn(j, newArray);
        }
    }

    /**
     * Parameter is the matrix containing image vectors. It is turned into an array, and a new array created with 1
     * extra row. Elements are transferred to the new array, and the sum of each column added to extra row at the end
     * @param weightedMatrix - matrix with image vectors
     * @return weightsArray2 - array with image vectors and column sums
     */
    public double [][] createWeightsArray(RealMatrix weightedMatrix)    {
        double [][] weightsArray1 = weightedMatrix.getData();
        double [][] weightsArray2 = new double [weightsArray1.length+1][weightsArray1[0].length];
        int a = weightsArray1.length;
        int b = weightsArray1[0].length;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                weightsArray2[i][j] = weightsArray1[i][j];
            }
        }
        int c = weightsArray2.length-1;
        int d = weightsArray2[0].length;
        for (int i = 0; i < d; i++) {
            double x = 0;
            for (int j = 0; j < c; j++) {
                x += weightsArray2[j][i];
                weightsArray2[c][i] = x;
            }
        }
        return weightsArray2;
    }

    /**
     * Array of pixel weightings is taken in. Final row values (sum of column) are transferred to a new array to be
     * stored in the database for matching
     *
     * 'lastRow' calculation taken from user compski on Stack Overflow page:
     * https://stackoverflow.com/questions/5134555/how-to-convert-a-1d-array-to-2d-array
     * @param inputArray - array of image vectors
     * @param noOfPC - number of principle components to be saved
     * @return - array with set of final weights for each image
     */
    public double [][] createWeightsTable(double [][] inputArray, int noOfPC)    {
        int a = inputArray.length;
        int b = inputArray[0].length;
        weightsTable = new double [b/noOfPC][noOfPC];
        double [] lastRow = new double [b];
        for (int i = 0; i < b; i++) {
            lastRow[i] = inputArray[a-1][i];
        }
        int y = weightsTable.length;
        int x = weightsTable[0].length;
        for(int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                weightsTable[i][j] = lastRow[j%x+i*x];
            }
        }
        return weightsTable;
    }

    /**
     * Accessor for 1D array created from a 2D array input
     * @return flatArray - 1D array
     */
    public double[] getFlatArray()  {
        return flatArray; }

    /**
     * Accessor for blank Matrix
     * @return pcMatrix - blank matrix
     */
    public Array2DRowRealMatrix getPCMatrix() {
        return pcMatrix;
    }

    /**
     * Accessor for final image weights table
     * @return weightstable - final weights table
     */
    public double[][] getWeightsTable() {
        return weightsTable;
    }

}
