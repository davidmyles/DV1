package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * Creates RealMatrix objects from an Array
 */
public class CreateRealMatrix {

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
    public void PopulateTrainingMatrix(Array2DRowRealMatrix baseMatrix, double[] inputArray, int column) {
        baseMatrix.setColumn(column, inputArray);
    }

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

    public void createPCMatrix()    {
        pcMatrix = new Array2DRowRealMatrix(9,6);
    }

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

    public void createWeightsTable(double [][] inputArray)  {
        weightsTable = new double[3][2];
        weightsTable[0][0] = inputArray[9][0];
        weightsTable[0][1] = inputArray[9][1];
        weightsTable[1][0] = inputArray[9][2];
        weightsTable[1][1] = inputArray[9][3];
        weightsTable[2][0] = inputArray[9][4];
        weightsTable[2][1] = inputArray[9][5];
    }

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
         * Takes a 1D array and uses to populate a parameter-specified row of a RealMatrix
         */
        public void addImageDataset ( int row, double[] trainingInputArray){
            trainingMatrix.setRow(row, trainingInputArray);
        }

        public RealMatrix getPCMatrix() {
            return pcMatrix;
        }

        public double[][] getWeightsTable() {
            return weightsTable;
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




