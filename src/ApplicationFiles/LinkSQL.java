package ApplicationFiles;

import java.sql.*;

/**
 * Class containing methods which link main system with the database
 */
public class LinkSQL {

    private int imageID;
    private int matchResult;

    /**
     * Saves average values array to the database.
     *
     * Note: not used in final implementation due to performance issues. Left in as a valid working alternative for
     * this function given different database constraints
     *
     * @param inputArray contains average values for each pixel
     * @throws SQLException
     */
    public void savePixelAveragesToDB(double [] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        for (int i = 0; i < inputArray.length; i++)   {
            ps.setInt(1,(i+1));
            ps.setDouble(2, inputArray[i]);
            ps.executeUpdate();
        }
    }

    /**
     * Saves array of 4 vectors to the database
     *
     * Note: not used in final implementation due to performance issues. Left in as a valid working alternative for
     * this function
     *
     * @param inputArray contains the U matrix from SVD performed on normalised pixel value array
     * @throws SQLException
     */
    public void saveVectorsToDB(double [][] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into principle_components (p_id,pc_1,pc_2,pc_3,pc_4) values (?,?,?,?,?)");
        for (int i = 0; i < inputArray.length; i++) {
                ps.setString(1, String.valueOf((i+1)));
                ps.setDouble(2, inputArray[i][0]);
                ps.setDouble(3, inputArray[i][1]);
                ps.setDouble(4, inputArray[i][2]);
                ps.setDouble(5, inputArray[i][3]);
                ps.executeUpdate();
        }
    }

    /**
     * Saves weights array to the database to be used for matching functionality
     *
     * @param inputArray 2D array containing 4 x weights for each image in training set
     * @throws SQLException
     */
    public void saveWeightsTableToDB(double [][] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into weights (image_id,weight_1, weight_2, weight_3, weight_4) values (?,?,?,?,?)");
        for (int i = 0; i < inputArray.length; i++) {
            ps.setInt(1, (i+1));
            ps.setDouble(2, inputArray[i][0]);
            ps.setDouble(3, inputArray[i][1]);
            ps.setDouble(4, inputArray[i][2]);
            ps.setDouble(5, inputArray[i][3]);
            ps.executeUpdate();
        }
    }

    /**
     * User is defined in the matching process (simulating the start of biometric ID process). ImageID for that user is
     * retrieved, to determine if it matches that identified by the system from the unknown image.
     *
     * @param userID pk of user table, denotes user who is undergoing matching
     * @return imageID of user, to be matched with input image
     * @throws SQLException
     */
    public int retrieveUserImageID(String userID) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        String sql = "select image_ID from users where user_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userID);
        ResultSet r1 = ps.executeQuery();
            while (r1.next()) {
                 int imageID = r1.getInt("image_id");
                 return imageID;
            }
            conn.close();
            return imageID;
    }


    /**
     * Performs matching operation based on comparison of weights from the unknown image with those stored in weights
     * table. 1 of 4 operations is performed base on number of matching vectors selected.
     *
     * @param weightsArray array of 4 x image weights, generated from processing of input image.
     * @param vectors number of vectors (1-4) chosen for matching comparison/selection.
     * @return imageID from stored weights which most closely matches inputted weights
     * @throws SQLException
     */
    public int matchImage(double [] weightsArray, int vectors) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        String sql1 = "SELECT IMAGE_ID\n" +
                "FROM weights\n" +
                "WHERE (((weight_1-?)*(weight_1-?))) =\n" +
                "(SELECT MIN(((weight_1 - ?)*(weight_1 - ?)))\n" +
                "FROM WEIGHTS)\n" +
                "GROUP BY IMAGE_ID";
        String sql2 = "SELECT IMAGE_ID\n" +
                "FROM weights\n" +
                "WHERE (((weight_1-?)*(weight_1-?)) + ((weight_2-?)*(weight_2-?)))=\n" +
                "(SELECT MIN(((weight_1 - ?)*(weight_1 - ?)) + ((weight_2 - ?)*(weight_2 - ?)))\n" +
                "FROM WEIGHTS)\n" +
                "GROUP BY IMAGE_ID";
        String sql3 = "SELECT IMAGE_ID\n" +
                "FROM weights\n" +
                "WHERE (((weight_1-?)*(weight_1-?)) + ((weight_2-?)*(weight_2-?)) + ((weight_3-?)*(weight_3-?)))=\n" +
                "(SELECT MIN(((weight_1-?)*(weight_1-?)) + ((weight_2-?)*(weight_2-?)) + ((weight_3-?)*(weight_3-?)))\n" +
                "FROM WEIGHTS)\n" +
                "GROUP BY IMAGE_ID";
        String sql4 = "SELECT IMAGE_ID\n" +
                "FROM weights\n" +
                "WHERE (((weight_1-?)*(weight_1-?)) + ((weight_2-?)*(weight_2-?)) + ((weight_3-?)*(weight_3-?)) + ((weight_4-?)*(weight_4-?)))=\n" +
                "(SELECT MIN(((weight_1-?)*(weight_1-?)) + ((weight_2-?)*(weight_2-?)) + ((weight_3-?)*(weight_3-?)) + ((weight_4-?)*(weight_4-?)))\n" +
                "FROM WEIGHTS)\n" +
                "GROUP BY IMAGE_ID";
        if (vectors == 1) {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setDouble(1, weightsArray[0]);
            ps1.setDouble(2, weightsArray[0]);
            ps1.setDouble(3, weightsArray[0]);
            ps1.setDouble(4, weightsArray[0]);
            ResultSet r1 = ps1.executeQuery();
            while (r1.next()) {
                int matchResult = r1.getInt(1);
                return matchResult;
            }
        }
        if (vectors == 2) {
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setDouble(1, weightsArray[0]);
            ps2.setDouble(2, weightsArray[0]);
            ps2.setDouble(3, weightsArray[1]);
            ps2.setDouble(4, weightsArray[1]);
            ps2.setDouble(5, weightsArray[0]);
            ps2.setDouble(6, weightsArray[0]);
            ps2.setDouble(7, weightsArray[1]);
            ps2.setDouble(8, weightsArray[1]);
            ResultSet r2 = ps2.executeQuery();
            while (r2.next()) {
                int matchResult = r2.getInt(1);
                return matchResult;
            }
        }
        if (vectors == 3) {
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setDouble(1, weightsArray[0]);
            ps3.setDouble(2, weightsArray[0]);
            ps3.setDouble(3, weightsArray[1]);
            ps3.setDouble(4, weightsArray[1]);
            ps3.setDouble(5, weightsArray[2]);
            ps3.setDouble(6, weightsArray[2]);
            ps3.setDouble(7, weightsArray[0]);
            ps3.setDouble(8, weightsArray[0]);
            ps3.setDouble(9, weightsArray[1]);
            ps3.setDouble(10, weightsArray[1]);
            ps3.setDouble(11, weightsArray[2]);
            ps3.setDouble(12, weightsArray[2]);
            ResultSet r3 = ps3.executeQuery();
            while (r3.next()) {
                int matchResult = r3.getInt(1);
                return matchResult;
            }
        }
        if (vectors == 4) {
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setDouble(1, weightsArray[0]);
            ps4.setDouble(2, weightsArray[0]);
            ps4.setDouble(3, weightsArray[1]);
            ps4.setDouble(4, weightsArray[1]);
            ps4.setDouble(5, weightsArray[2]);
            ps4.setDouble(6, weightsArray[2]);
            ps4.setDouble(7, weightsArray[3]);
            ps4.setDouble(8, weightsArray[3]);
            ps4.setDouble(9, weightsArray[0]);
            ps4.setDouble(10, weightsArray[0]);
            ps4.setDouble(11, weightsArray[1]);
            ps4.setDouble(12, weightsArray[1]);
            ps4.setDouble(13, weightsArray[2]);
            ps4.setDouble(14, weightsArray[2]);
            ps4.setDouble(15, weightsArray[3]);
            ps4.setDouble(16, weightsArray[3]);
            ResultSet r4 = ps4.executeQuery();
            while (r4.next()) {
                int matchResult = r4.getInt(1);
                return matchResult;
            }
        }
            conn.close();
            return matchResult;
    }

    /**
     * Clears avg_values table at the start of training process
     *
     * Note: avg_values not currently used as described above
     *
     * @throws SQLException
     */
    public void clearAvgValues() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("DELETE from avg_values");
        ps.executeUpdate();
        conn.close();
    }

    /** Clears principle_components table at the start of training process
     *
     * Note: principle_components not currently used as described above
     */
    public void clearPrincipleComponents() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("DELETE from principle_components");
        ps.executeUpdate();
        conn.close();
    }

    /**
     * Clears weights table at the start of training process
     * @throws SQLException
     */
    public void clearWeights() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("DELETE from weights");
        ps.executeUpdate();
        conn.close();
    }
}
