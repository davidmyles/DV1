package ApplicationFiles;

import java.sql.*;

public class LinkSQL {

    private String matchResult;

    /*public void savePixelAveragesToDB(double [][]inputArray) throws SQLException {
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
    }*/

    public void savePixelAveragesToDB(double [] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        for (int i = 0; i < inputArray.length; i++)   {
            ps.setInt(1,(i+1));
            ps.setDouble(2, inputArray[i]);
            ps.executeUpdate();
        }
    }

    /*public void savePrincipleComponentsToDB(double [][]weightsArray) throws SQLException {
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
    }*/

    public void savePrincipleComponentsToDB(double [][] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into principle_components (p_id,pc_1,pc_2,pc_3,pc_4) values (?,?,?,?,?)");
        for (int i = 0; i < inputArray.length; i++) {
                ps.setString(1, String.valueOf((i+1)));
                ps.setDouble(2, inputArray[i][0]);
                ps.setDouble(3, inputArray[i][1]);
                ps.setDouble(4, inputArray[i][2]);
                ps.setDouble(5, inputArray[i][3]);
                //ps.setDouble(5, inputArray[i][4]);
                //ps.setDouble(6, inputArray[i][5]);
                ps.executeUpdate();
            }
    }


    /*public void saveWeightsTableToDB(double[][] weightsArray) throws SQLException {
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
    }*/

    /*public void saveWeightsTableToDB2(double[][] weightsArray) throws SQLException {
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
        PreparedStatement ps4 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps4.setString(1, "image_4");
        ps4.setDouble(2, weightsArray[3][0]);
        ps4.setDouble(3, weightsArray[3][1]);
        ps4.executeUpdate();
        PreparedStatement ps5 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps5.setString(1, "image_5");
        ps5.setDouble(2, weightsArray[4][0]);
        ps5.setDouble(3, weightsArray[4][1]);
        ps5.executeUpdate();
        PreparedStatement ps6 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps6.setString(1, "image_6");
        ps6.setDouble(2, weightsArray[5][0]);
        ps6.setDouble(3, weightsArray[5][1]);
        ps6.executeUpdate();
        PreparedStatement ps7 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps7.setString(1, "image_7");
        ps7.setDouble(2, weightsArray[6][0]);
        ps7.setDouble(3, weightsArray[6][1]);
        ps7.executeUpdate();
        PreparedStatement ps8 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps8.setString(1, "image_8");
        ps8.setDouble(2, weightsArray[7][0]);
        ps8.setDouble(3, weightsArray[7][1]);
        ps8.executeUpdate();
        PreparedStatement ps9 = conn.prepareStatement("insert into weights (image_id, weight_1, weight_2) values (?,?,?)");
        ps9.setString(1, "image_9");
        ps9.setDouble(2, weightsArray[8][0]);
        ps9.setDouble(3, weightsArray[8][1]);
        ps9.executeUpdate();
        conn.close();
    }*/

    public void saveWeightsTableToDBx2PC(double [][] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into weights (weight_1, weight_2) values (?,?)");
        for (int i = 0; i < inputArray.length; i++) {
                ps.setDouble(1, inputArray[i][0]);
                ps.setDouble(2, inputArray[i][1]);
                ps.executeUpdate();
        }
    }

    public void saveWeightsTableToDBx4PC(double [][] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into weights (weight_1, weight_2, weight_3, weight_4) values (?,?,?,?)");
        for (int i = 0; i < inputArray.length; i++) {
            ps.setDouble(1, inputArray[i][0]);
            ps.setDouble(2, inputArray[i][1]);
            ps.setDouble(3, inputArray[i][2]);
            ps.setDouble(4, inputArray[i][3]);
            ps.executeUpdate();
        }
    }

    public void saveUnkownWeightsToDBx2PC(double[][] weightsArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("insert into unknown_weights (unknown_image_id, unknown_weight_1, unknown_weight_2) values (?,?,?)");
        ps1.setString(1, "unknown_1");
        ps1.setDouble(2, weightsArray[0][0]);
        ps1.setDouble(3, weightsArray[0][1]);
        ps1.executeUpdate();
        conn.close();
    }

    public void saveUnkownWeightsToDBx4PC(double[][] weightsArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("insert into unknown_weights (unknown_image_id, unknown_weight_1, unknown_weight_2, unknown_weight_3, unknown_weight_4) values (?,?,?,?,?)");
        ps1.setString(1, "unknown_1");
        ps1.setDouble(2, weightsArray[0][0]);
        ps1.setDouble(3, weightsArray[0][1]);
        ps1.setDouble(4, weightsArray[0][2]);
        ps1.setDouble(5, weightsArray[0][3]);
        ps1.executeUpdate();
        conn.close();
    }


    public String matchImage() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        String sql = "SELECT IMAGE_ID\n" +
                     "FROM weights, unknown_weights\n" +
                     "WHERE (((weight_1-unknown_weight_1)*(weight_1-unknown_weight_1)) + ((weight_2-unknown_weight_2)*(weight_2-unknown_weight_2)))=\n" +
                     "(SELECT MIN(((weight_1 - unknown_weight_1)*(weight_1 - unknown_weight_1)) + ((weight_2 - unknown_weight_2)*(weight_2 - unknown_weight_2)))\n" +
                     "FROM WEIGHTS, UNKNOWN_WEIGHTS)\n" +
                     "GROUP BY IMAGE_ID";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet r1 = ps.executeQuery();
        while (r1.next())   {
            String matchResult = r1.getString(1);
            System.out.println("System Match With: Image " + matchResult);
            return matchResult;
        }
        conn.close();
        return matchResult;
    }

    public String matchImage2() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        String sql = "SELECT IMAGE_ID\n" +
                "FROM weights, unknown_weights\n" +
                "WHERE (((weight_1-unknown_weight_1)*(weight_1-unknown_weight_1)) + ((weight_2-unknown_weight_2)*(weight_2-unknown_weight_2)) + ((weight_3-unknown_weight_3)*(weight_3-unknown_weight_3)) + ((weight_4-unknown_weight_4)*(weight_4-unknown_weight_4)))=\n" +
                "(SELECT MIN(((weight_1-unknown_weight_1)*(weight_1-unknown_weight_1)) + ((weight_2-unknown_weight_2)*(weight_2-unknown_weight_2)) + ((weight_3-unknown_weight_3)*(weight_3-unknown_weight_3)) + ((weight_4-unknown_weight_4)*(weight_4-unknown_weight_4)))\n" +
                "FROM WEIGHTS, UNKNOWN_WEIGHTS)\n" +
                "GROUP BY IMAGE_ID";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet r1 = ps.executeQuery();
        while (r1.next()) {
            String matchResult = r1.getString(1);
            System.out.println("System Match With: Image " + matchResult);
            return matchResult;
        }
        conn.close();
        return matchResult;
    }

    public void resetWeightsImageID() throws SQLException   {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("ALTER TABLE WEIGHTS MODIFY(IMAGE_ID Generated as Identity (START WITH 1))");
        ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("DROP TABLE WEIGHTS");
        ps2.executeUpdate();
        PreparedStatement ps3 = conn.prepareStatement("CREATE TABLE weights\t(\n" +
                "                                     image_ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,\n" +
                "                                     weight_1 varchar2(50),\n" +
                        "weight_2 varchar2(50),\n" +
                        "weight_3 varchar2(50),\n" +
                "                                     weight_4 varchar2(50))");
        ps3.executeUpdate();
        conn.close();
    }

    public void clearUnknownImageWeights() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        //PreparedStatement ps1 = conn.prepareStatement("ALTER TABLE AVG_VALUES MODIFY(P_ID Generated as Identity (START WITH 1));");
        //ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("DELETE from unknown_weights");
        ps2.executeUpdate();
        conn.close();
    }
    public void clearAvgValues() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("DELETE from avg_values");
        ps.executeUpdate();
        conn.close();
    }

    public void clearAvgValues2() throws SQLException   {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("ALTER TABLE AVG_VALUES MODIFY(P_ID Generated as Identity (START WITH 1))");
        ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("DROP TABLE AVG_VALUES");
        ps2.executeUpdate();
        PreparedStatement ps3 = conn.prepareStatement("CREATE TABLE AVG_VALUES\t(\n" +
                "                                     p_ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,\n" +
                "                                     avg_pv varchar2(50))");
        ps3.executeUpdate();
        conn.close();
    }

    public void clearPrincipleComponents() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("ALTER TABLE PRINCIPLE_COMPONENTS MODIFY(PIXEL_ID Generated as Identity (START WITH 1))");
        ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("DROP TABLE PRINCIPLE_COMPONENTS");
        ps2.executeUpdate();
        PreparedStatement ps3 = conn.prepareStatement("CREATE TABLE principle_components\t(\n" +
                "                                     pixel_ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,\n" +
                "                                     pc_1 varchar2(50),\n" +
                "                                     pc_2 varchar2(50),\n" +
                "                                     pc_3 varchar2(50),\n" +
                "                                     pc_4 varchar2(50),\n" +
                "                                     pc_5 varchar2(50),\n" +
                "                                     pc_6 varchar2(50))");
        ps3.executeUpdate();
        conn.close();
    }

    public void clearPrincipleComponentsX2() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("DELETE from principle_components");
        ps.executeUpdate();
        conn.close();
    }

    public void clearWeights() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("DELETE from weights");
        ps.executeUpdate();
        conn.close();
    }
}
