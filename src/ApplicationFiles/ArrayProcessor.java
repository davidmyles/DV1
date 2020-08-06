package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.stream.DoubleStream;

public class ArrayProcessor {

    Array2DRowRealMatrix pcMatrix;
    private double[][] weightsTable;

    /**
     * Parameter is a populated matrix. Average value for elements in each row are calculated, with a new column for
     * average row values added at the end of existing matrix
     */
    public Array2DRowRealMatrix getAvgValues(Array2DRowRealMatrix baseMatrix, int divider) {
        int a = baseMatrix.getColumnDimension() - 1;
        int b = baseMatrix.getRowDimension();
        for (int i = 0; i < b; i++) {
            double[] c = baseMatrix.getRow(i);
            int d = ((int) DoubleStream.of(c).sum() / divider);
            baseMatrix.addToEntry(i, a, d);
        }
        return baseMatrix;
    }

    /**
     * Takes the RealMatrix with average row values and transfers those values into a new 1D array
     */
    public double [] getAvgValuesArrayForDB(RealMatrix inputMatrix)  {
        double [] outputArray = inputMatrix.getColumn((inputMatrix.getColumnDimension()-1));
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
        return d2;
    }

    /**
     * Creates a blank Matrix to be receive columns of pixel weight data
     */
    public void createPCMatrix(int rows, int columns)    {
        pcMatrix = new Array2DRowRealMatrix(rows,columns);
    }

    /**
     * Takes a normalised pixel array and matrix of matching vectors. The columns of the 2D array are split
     * into a series of 3 x 1D arrays, each representing an image. Each column element is multiplied by the 1st vector
     * to create a new column of pixel weightings. This is repeated for each column using the 2nd,3rd & 4th vectors.
     * The result is n columns (x images x 4 vectors), which are placed in a new matrix.
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
     * Array of pixel weightings is taken in. Final row values (sum of column) are transferred to a new array to be
     * stored in the databse for matching
     *
     * 'lastRow' calculation taken from user compski on Stack Overflow page:
     *  https://stackoverflow.com/questions/5134555/how-to-convert-a-1d-array-to-2d-array
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
