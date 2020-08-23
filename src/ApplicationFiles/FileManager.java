package ApplicationFiles;

import org.apache.commons.math3.linear.RealMatrix;

import java.io.*;

/**
 * Handles file input/output and processing stored data to correct formats for system training/matching
 */
public class FileManager {

    /**
     * Takes a matrix of vectors and returns a 2D array. As the matching function will use up to 4 vectors, only the
     * first 4 columns are transferred to the array.
     * @param inputMatrix - vector matrix
     * @return outputArray - array with 1st 4 columns of vectors
     */
    public double [][] reduceVectors(RealMatrix inputMatrix)   {
        double [][] d1 = inputMatrix.getData();
        double [][] outputArray = new double [d1.length][4];
        for (int i = 0; i < outputArray.length; i++) {
            for (int j = 0; j < outputArray[0].length; j++) {
                outputArray[i][j] = d1[i][j];
            }
        }
        return outputArray;
    }

    /**
     * Takes a 2D array of vectors and saves to file as txt with csv formatting.
     *
     *  Detail on formatting taken from Stack Overflow user Tomasso Pasini at
     *  https://stackoverflow.com/questions/34958829/how-to-save-a-2d-array-into-a-text-file-with-bufferedwriter
     * @param inputArray - array to be saved
     * @param filename - file storage location
     * @throws IOException
     */
    public static void saveVectorsToFile(double [][] inputArray, String filename) throws IOException  {
        StringBuilder vectors = new StringBuilder();
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[0].length; j++) {
                vectors.append(inputArray[i][j] + "");
                if (j < inputArray.length - 1)
                    vectors.append(",");
                    }
                    vectors.append("\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(vectors.toString());
        writer.close();
    }

    /**
     * Takes a 1D array of average pixel values and saves to file as txt with csv formatting.
     *
     * Detail on formatting taken from Stack Overflow user Tomasso Pasini at
     * https://stackoverflow.com/questions/34958829/how-to-save-a-2d-array-into-a-text-file-with-bufferedwriter
     * @param inputArray - array of pixel values
     * @param filename - file storage location
     * @throws IOException
     */
    public static void saveAvgValuesToFile(double [] inputArray, String filename) throws IOException  {
        StringBuilder avgValues = new StringBuilder();
        for (int i = 0; i < inputArray.length; i++) {
            avgValues.append(inputArray[i] + "");
            avgValues.append(",");
            avgValues.append("\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(avgValues.toString());
        writer.close();
    }

    /**
     * Retrieves txt file containing vectors from files and transfers values into a new 2D array
     *
     * Detail on formatting taken from Stack Overflow user Tomasso Pasini at
     * https://stackoverflow.com/questions/34958829/how-to-save-a-2d-array-into-a-text-file-with-bufferedwriter
     * @param savedVectors - vectors file storage location
     * @param pixels - total number of pixels from 1 image in file
     * @return - array of retrieved vector values
     * @throws IOException
     */
    public static double [][] retrieveVectorsFromFile(String savedVectors, int pixels) throws IOException {
        double [][] vectorArray = new double[pixels][4];
        BufferedReader reader = new BufferedReader(new FileReader(savedVectors));
        String line = "";
        int row = 0;
        while((line = reader.readLine()) != null) {
            String[] cols = line.split(",");
            int col = 0;
            for(String  c : cols) {
                vectorArray[row][col] = Double.parseDouble(c);
                col++; }
            row++; }
        reader.close();
        return vectorArray;
    }

    /**
     * Retrieves txt file containing average values from files and transfers values into a new 1D array
     *
     * Detail on formatting taken from Stack Overflow user Tomasso Pasini at
     * https://stackoverflow.com/questions/34958829/how-to-save-a-2d-array-into-a-text-file-with-bufferedwriter
     * @param savedAvgValues - average values file storage location
     * @param pixels - total number of pixels from 1 image in file
     * @return - array of retrieved average values
     * @throws IOException
     */
    public static double [] retrieveAvgValuesFromFile(String savedAvgValues, int pixels) throws IOException {
        double [] avgValuesArray = new double[pixels];
        BufferedReader reader = new BufferedReader(new FileReader(savedAvgValues));
        String line = "";
        int row = 0;
        while((line = reader.readLine()) != null) {
            String[] cols = line.split(",");
            for(String  c : cols) {
                avgValuesArray[row] = Double.parseDouble(c); }
            row++; }
        reader.close();
        return avgValuesArray;
    }
}
