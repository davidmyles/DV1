package ApplicationFiles;

import java.sql.*;

public class LinkSQL {


    private String matchResult;

    public void savePixelAveragesToDB(double [] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into avg_values (p_id, avg_pv) values (?,?)");
        for (int i = 0; i < inputArray.length; i++)   {
            ps.setInt(1,(i+1));
            ps.setDouble(2, inputArray[i]);
            ps.executeUpdate();
        }
    }

    public void savePrincipleComponentsToDB(double [][] inputArray) throws SQLException {
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

    public void saveWeightsTableToDBx2PC(double [][] inputArray) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into weights (weight_1, weight_2) values (?,?)");
        for (int i = 0; i < inputArray.length; i++) {
                ps.setDouble(1, inputArray[i][0]);
                ps.setDouble(2, inputArray[i][1]);
                ps.executeUpdate();
        }
    }

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


    public void saveUnkownWeightsToDB(double[][] weightsArray) throws SQLException {
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


    public String matchImage2Vectors() throws SQLException {
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

    public String matchImage3Vectors() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        String sql = "SELECT IMAGE_ID\n" +
                "FROM weights, unknown_weights\n" +
                "WHERE (((weight_1-unknown_weight_1)*(weight_1-unknown_weight_1)) + ((weight_2-unknown_weight_2)*(weight_2-unknown_weight_2)) + ((weight_3-unknown_weight_3)*(weight_3-unknown_weight_3)))=\n" +
                "(SELECT MIN(((weight_1-unknown_weight_1)*(weight_1-unknown_weight_1)) + ((weight_2-unknown_weight_2)*(weight_2-unknown_weight_2)) + ((weight_3-unknown_weight_3)*(weight_3-unknown_weight_3)))\n" +
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

    public String matchImage4Vectors() throws SQLException {
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

    public void clearUnknownImageWeights() throws SQLException  {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
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

    public void clearPrincipleComponents() throws SQLException  {
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
