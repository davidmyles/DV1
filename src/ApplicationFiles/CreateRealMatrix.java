package ApplicationFiles;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * Creates RealMatrix objects from an Array
 */
public class CreateRealMatrix {

    //double[][] imageInputArray;
    Array2DRowRealMatrix imageMatrix;
    Array2DRowRealMatrix trainingMatrix;
    double [][] imageWeightsArray;
    //int row;
    //int column;

    /**
     * Takes an array as a parameter and creates a RealMatrix with the array values
     */
    public void CreateImageRealMatrix(double[][] imageInputArray) {
        imageMatrix = new Array2DRowRealMatrix(imageInputArray);
    }

    /**
     * Creates a blank RealMatrix
     */
    public void CreateTrainingRealMatrix(int row, int column)  {
        trainingMatrix = new Array2DRowRealMatrix(row, column);
    }

    /**
     * Takes a blank RealMatrix and 1D array as parameters. Inserts the array values into the matrix at the specified row
     */
    public void PopulateTrainingMatrix(Array2DRowRealMatrix baseMatrix, double [] inputArray, int column)  {
        baseMatrix.setColumn(column, inputArray);
    }

    /**
     * Parameter is a populated matrix. Average value for elements in each row are calculated, with a new column for
     * average row values added at the end of existing matrix
     */
    public Array2DRowRealMatrix getAvgValues(Array2DRowRealMatrix baseMatrix)   {
        int a = baseMatrix.getColumnDimension()-1;
        int b = baseMatrix.getRowDimension();
            for (int i = 0; i < b; i++) {
                double[] c = baseMatrix.getRow(i);
                int d = ((int) DoubleStream.of(c).sum()/3);
                baseMatrix.addToEntry(i, a, d);
        }
        return baseMatrix;
    }

    /**
     * Takes the average row value and subtracts from each element in that row
     */
    public Array2DRowRealMatrix applyAvgValues(Array2DRowRealMatrix inputMatrix) {
        int c = inputMatrix.getColumnDimension()-1;
        double z = inputMatrix.getRowDimension();
        double x = inputMatrix.getColumnDimension();
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < x; j++) {
                double g = inputMatrix.getEntry(i, j);
                double h = inputMatrix.getEntry(i,c);
                double f = g-h;
                inputMatrix.setEntry(i, j, f);
            }
        }
        return inputMatrix;
    }

    /**
     * Creates a new array from the processed RealMatrix, with the average values column removed
     */
    public double [][] finaliseAvgValues(Array2DRowRealMatrix inputMatrix) {
        double [][] d1 = inputMatrix.getData();
        int a = d1.length;
        int b = d1[0].length-1;
        double [][] d2 = new double [a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                d2[i][j] = d1[i][j];
            }
        }
        System.out.println(Arrays.deepToString(d2));
        return d2;
    }

    public double [][] createImageWeightsArray(double [][] normalisedArray)   {
        double [][] imageWeightsArray = new double [normalisedArray.length][normalisedArray[0].length+1];
        return imageWeightsArray;
    }


    public void calculateImageWeights(double [][] imageWeightsArray, double [][] normalisedArray, RealMatrix principleComponents, int x) {
        //for (int i = 0; i < d1[0].length; i++) {
            for (int j = 0; j < imageWeightsArray.length; j++) {
                imageWeightsArray[j][x] = normalisedArray[j][x]*principleComponents.getEntry(j, x);
            }

    }

    /**
     * Takes a 1D array and uses to populate a parameter-specified row of a RealMatrix
     */
    public void addImageDataset(int row, double [] trainingInputArray)   {
        trainingMatrix.setRow(row, trainingInputArray);
    }

    public double [][] getImageWeightsArray()   {
        return imageWeightsArray;
    }

    /**
     * Accessor for matching image RealMatrix object
     */
    public Array2DRowRealMatrix getImageMatrix() {
        return imageMatrix;
    }

    /**
     * Accessor for training image RealMatrix object
     */
    public Array2DRowRealMatrix getTrainingMatrix() {
        return trainingMatrix;
    }
}



