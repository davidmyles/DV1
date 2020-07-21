package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class ArrayProcessor {

    Array2DRowRealMatrix pcMatrix;
    private double[][] weightsTable;

    /**
     * Parameter is a populated matrix. Average value for elements in each row are calculated, with a new column for
     * average row values added at the end of existing matrix
     */
    public Array2DRowRealMatrix getAvgValues(Array2DRowRealMatrix baseMatrix) {
        int a = baseMatrix.getColumnDimension() - 1;
        int b = baseMatrix.getRowDimension();
        for (int i = 0; i < b; i++) {
            double[] c = baseMatrix.getRow(i);
            int d = ((int) DoubleStream.of(c).sum() / 3);
            baseMatrix.addToEntry(i, a, d);
        }
        return baseMatrix;
    }

    public double [][] getArrayFromMatrix(RealMatrix inputMatrix)  {
        double [][] outputArray = inputMatrix.getData();
        return outputArray;
    }

    /**
     * Takes the average row value and subtracts from each element in that row
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
     * Creates a new array from the processed RealMatrix, with the average values column removed
     */
    public double[][] finaliseAvgValues(Array2DRowRealMatrix inputMatrix) {
        double[][] d1 = inputMatrix.getData();
        int a = d1.length;
        int b = d1[0].length - 1;
        double[][] d2 = new double[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                d2[i][j] = d1[i][j];
            }
        }
        System.out.println(Arrays.deepToString(d2));
        return d2;
    }

    /**
     * Creates a blank Matrix to be receive columns of pixel weight data
     */
    public void createPCMatrix()    {
        pcMatrix = new Array2DRowRealMatrix(9,6);
    }

    /**
     * Takes a normalised pixel array and matrix of matching principle components. The columns of the 2D array are split
     * into a series of 3 x 1D arrays, each representing an image. Each column element is multiplied by the 1st principle
     * component to create a new column of pixel weightings. This is repeated for each column using the 2nd principle
     * component. The result is 6 columns (3 images x 2 principle components), which are placed in a new matrix.
     */
    public void calculateImageWeights(double[][] inputArray, RealMatrix principleComponents, int x, int y, int z) {
        double [] columnArray = new double[inputArray.length];
        double [][] matrixArray = principleComponents.getData();
        for (int i = 0; i < inputArray.length; i++) {
            columnArray[i] = inputArray[i][x];
        }
        for (int i = 0; i < columnArray.length; i++) {
            columnArray[i] = columnArray[i]*matrixArray[i][y];
            pcMatrix.setColumn(z,columnArray);
        }
    }

    /**
     * Parameter is the matrix containing pixel weightings. It is turned into an array, and a new array created with 1
     * extra row. Elements are transferred to new array, and the sum of each column added to extra row.
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
     * Parameter is weighted array with column totals. The total values are moved to a new (transposed) matrix, giving
     * 2 weightings for each image
     */
    public void createWeightsTable(double [][] inputArray)  {
        weightsTable = new double[3][2];
        weightsTable[0][0] = inputArray[9][0];
        weightsTable[0][1] = inputArray[9][1];
        weightsTable[1][0] = inputArray[9][2];
        weightsTable[1][1] = inputArray[9][3];
        weightsTable[2][0] = inputArray[9][4];
        weightsTable[2][1] = inputArray[9][5];
    }

    /**
     * Accessor for blank Matrix
     */
    public RealMatrix getPCMatrix() {
        return pcMatrix;
    }

    /**
     * Accessor for final image weights table
     */
    public double[][] getWeightsTable() {
        return weightsTable;
    }

}
