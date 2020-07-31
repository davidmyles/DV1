package ApplicationFiles;

import java.sql.*;

public class LinkSQL {

    private String matchResult;

    public void savePixelAveragesToDB(double [][]inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps1.setString(1, "p1_1");
        ps1.setDouble(2, inputArray[0][inputArray[0].length-1]);
        ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps2.setString(1, "p1_2");
        ps2.setDouble(2, inputArray[1][inputArray[0].length-1]);
        ps2.executeUpdate();
        PreparedStatement ps3 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps3.setString(1, "p1_3");
        ps3.setDouble(2, inputArray[2][inputArray[0].length-1]);
        ps3.executeUpdate();
        PreparedStatement ps4 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps4.setString(1, "p1_4");
        ps4.setDouble(2, inputArray[3][inputArray[0].length-1]);
        ps4.executeUpdate();
        PreparedStatement ps5 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps5.setString(1, "p1_5");
        ps5.setDouble(2, inputArray[4][inputArray[0].length-1]);
        ps5.executeUpdate();
        PreparedStatement ps6 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps6.setString(1, "p1_6");
        ps6.setDouble(2, inputArray[5][inputArray[0].length-1]);
        ps6.executeUpdate();
        PreparedStatement ps7 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps7.setString(1, "p1_7");
        ps7.setDouble(2, inputArray[6][inputArray[0].length-1]);
        ps7.executeUpdate();
        PreparedStatement ps8 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps8.setString(1, "p1_8");
        ps8.setDouble(2, inputArray[7][inputArray[0].length-1]);
        ps8.executeUpdate();
        PreparedStatement ps9 = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        ps9.setString(1, "p1_9");
        ps9.setDouble(2, inputArray[8][inputArray[0].length-1]);
        ps9.executeUpdate();
        conn.close();
    }

    public void savePrincipleComponentsToDB(double [][]weightsArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps1.setString(1, "pixel_1");
        ps1.setDouble(2, weightsArray[0][0]);
        ps1.setDouble(3, weightsArray[0][1]);
        ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps2.setString(1, "pixel_2");
        ps2.setDouble(2, weightsArray[1][0]);
        ps2.setDouble(3, weightsArray[1][1]);
        ps2.executeUpdate();
        PreparedStatement ps3 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps3.setString(1, "pixel_3");
        ps3.setDouble(2, weightsArray[2][0]);
        ps3.setDouble(3, weightsArray[2][1]);
        ps3.executeUpdate();
        PreparedStatement ps4 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps4.setString(1, "pixel_4");
        ps4.setDouble(2, weightsArray[3][0]);
        ps4.setDouble(3, weightsArray[3][1]);
        ps4.executeUpdate();
        PreparedStatement ps5 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps5.setString(1, "pixel_5");
        ps5.setDouble(2, weightsArray[4][0]);
        ps5.setDouble(3, weightsArray[4][1]);
        ps5.executeUpdate();
        PreparedStatement ps6 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps6.setString(1, "pixel_6");
        ps6.setDouble(2, weightsArray[5][0]);
        ps6.setDouble(3, weightsArray[5][1]);
        ps6.executeUpdate();
        PreparedStatement ps7 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps7.setString(1, "pixel_7");
        ps7.setDouble(2, weightsArray[6][0]);
        ps7.setDouble(3, weightsArray[6][1]);
        ps7.executeUpdate();
        PreparedStatement ps8 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps8.setString(1, "pixel_8");
        ps8.setDouble(2, weightsArray[7][0]);
        ps8.setDouble(3, weightsArray[7][1]);
        ps8.executeUpdate();
        PreparedStatement ps9 = conn.prepareStatement("insert into principle_components_x2 (pixel_id, pc_1, pc_2) values (?,?,?)");
        ps9.setString(1, "pixel_9");
        ps9.setDouble(2, weightsArray[8][0]);
        ps9.setDouble(3, weightsArray[8][1]);
        ps9.executeUpdate();
        conn.close();
    }


    public void saveWeightsTableToDB(double[][] weightsArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps1.setString(1, "image_1");
        ps1.setDouble(2, weightsArray[0][0]);
        ps1.setDouble(3, weightsArray[0][1]);
        ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps2.setString(1, "image_2");
        ps2.setDouble(2, weightsArray[1][0]);
        ps2.setDouble(3, weightsArray[1][1]);
        ps2.executeUpdate();
        PreparedStatement ps3 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps3.setString(1, "image_3");
        ps3.setDouble(2, weightsArray[2][0]);
        ps3.setDouble(3, weightsArray[2][1]);
        ps3.executeUpdate();
        conn.close();
    }

    public void saveUnkownWeightsToDB(double[][] weightsArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("insert into unknown_weights (unknown_image_id, unknown_weight_1, unknown_weight_2) values (?,?,?)");
        ps1.setString(1, "unknown_1");
        ps1.setDouble(2, weightsArray[0][0]);
        ps1.setDouble(3, weightsArray[0][1]);
        ps1.executeUpdate();
        conn.close();
    }

    public String matchImage() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        String sql = "SELECT IMAGE_ID\n" +
                     "FROM weights, unknown_weights\n" +
                     "WHERE (((weight_1-unknown_weight_1)*(weight_1-unknown_weight_1)) + ((weight_2-unknown_weight_2)*(weight_2-unknown_weight_2))) =\n" +
                     "(SELECT MIN(((weight_1 - unknown_weight_1)*(weight_1 - unknown_weight_1)) + ((weight_2 - unknown_weight_2)*(weight_2 - unknown_weight_2))) FROM WEIGHTS, UNKNOWN_WEIGHTS)\n" +
                     "GROUP BY IMAGE_ID";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet r1 = ps.executeQuery();
        while (r1.next())   {
            String matchResult = r1.getString(1);
            System.out.println(matchResult);
            return matchResult;
        }
        conn.close();
        return matchResult;
    }

    public void clearUnknownImageWeights() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("DELETE from unknown_weights");
        ps.executeUpdate();
        conn.close();
    }
}
