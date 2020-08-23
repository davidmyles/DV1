package TestFiles;

import ApplicationFiles.LinkSQL;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for LinkSQL objects
 */
class LinkSQLTest {

    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");

    LinkSQLTest() throws SQLException {
    }

    /**
     * Creates a dummy array of pixel values & saves to database. Then retrieves the values into arraylist and checks
     * they match the original input.
     * @throws SQLException
     */
    @Test
    void savePixelAveragesToDB() throws SQLException {
        LinkSQL ls = new LinkSQL();
        ls.clearAvgValues();
        double [] d1 = new double[]{4,3,5,9,6,3,2,1,5,7,2,6};
        ls.savePixelAveragesToDB(d1);
        String sql = "select avg_pv from avg_values order by p_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ArrayList<Double> d2 = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            double p1 = rs.getDouble(1);
            d2.add(p1);
        }
        assertEquals(d1[0],d2.get(0));
        assertEquals(d1[2],d2.get(2));
        assertEquals(d1[4],d2.get(4));
    }

    /**
     * Creates a dummy array of vector values & saves to database. Then retrieves the values into 4 arraylists and
     * checks that they match the original input.
     * @throws SQLException
     */
    @Test
    void saveVectorstoToDB() throws SQLException {
        LinkSQL ls = new LinkSQL();
        ls.clearPrincipleComponents();
        double [][] d1 = new double[][]{{4,3,5,9},{2,1,9,3},{8,6,5,4},{1,7,3,2}};
        ls.saveVectorsToDB(d1);
        String sql = "select pc_1,pc_2,pc_3,pc_4 from principle_components order by p_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ArrayList<Double> d2 = new ArrayList<>();
        ArrayList<Double> d3 = new ArrayList<>();
        ArrayList<Double> d4 = new ArrayList<>();
        ArrayList<Double> d5 = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            double p1 = rs.getDouble(1);
            d2.add(p1);
            double p2 = rs.getDouble(2);
            d3.add(p2);
            double p3 = rs.getDouble(3);
            d4.add(p3);
            double p4 = rs.getDouble(4);
            d5.add(p4);
        }
        assertEquals(d1[0][0],d2.get(0));
        assertEquals(d1[1][1],d3.get(1));
        assertEquals(d1[2][2],d4.get(2));
        assertEquals(d1[3][3],d5.get(3));
    }

    /**
     * Creates a dummy array of weight values & saves to database. Then retrieves the values into 4 arraylists which
     * are added to a matrix. Test checks that matrix values match the original input.
     * @throws SQLException
     */
    @Test
    void saveWeightsTableToDB() throws SQLException {
        LinkSQL ls = new LinkSQL();
        ls.clearWeights();
        double [][] d1 = new double[][]{{4,3,5,9},{2,1,9,3},{8,6,5,4},{1,7,3,2}};
        ls.saveWeightsTableToDB(d1);
        String sql = "select weight_1,weight_2,weight_3,weight_4 from weights order by image_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ArrayList<Double> d2 = new ArrayList<>();
        ArrayList<Double> d3 = new ArrayList<>();
        ArrayList<Double> d4 = new ArrayList<>();
        ArrayList<Double> d5 = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            double p1 = rs.getDouble(1);
            d2.add(p1);
            double p2 = rs.getDouble(2);
            d3.add(p2);
            double p3 = rs.getDouble(3);
            d4.add(p3);
            double p4 = rs.getDouble(4);
            d5.add(p4);
        }
        Array2DRowRealMatrix rM = new Array2DRowRealMatrix(d2.size(),4);
        double[] d1a = new double[d2.size()];
        for (int i = 0; i < d1a.length; i++) {
            d1a[i] = d2.get(i);
        }
        double[] d2a = new double[d2.size()];
        for (int i = 0; i < d2a.length; i++)    {
            d2a[i] = d3.get(i);
        }
        double[] d3a = new double[d2.size()];
        for (int i = 0; i < d3a.length; i++) {
            d3a[i] = d4.get(i);
        }
        double[] d4a = new double[d2.size()];
        for (int i = 0; i < d4a.length; i++)    {
            d4a[i] = d5.get(i);
        }
        rM.setColumn(0, d1a);
        rM.setColumn(1, d2a);
        rM.setColumn(2, d3a);
        rM.setColumn(3, d4a);
        assertEquals(d1[0][0], rM.getEntry(0,0));
        assertEquals(d1[1][1], rM.getEntry(1,1));
        assertEquals(d1[2][2], rM.getEntry(2,2));
        assertEquals(d1[3][3], rM.getEntry(3,3));
    }

    /**
     * Tests that the method retrieves the correct image_id for the specified user
     * @throws SQLException
     */
    @Test
    void retrieveUserImageIDTest() throws SQLException {
        LinkSQL ls = new LinkSQL();
        int iDTest = 9;
        int unknown = ls.retrieveUserImageID("user_9");
        assertEquals(iDTest,unknown);
    }

    /**
     * Clears average values then saves new array to that table to ensure that its populated. Then clears again and runs
     * result set. Verifies that nothing is returned.
     * @throws SQLException
     */
    @Test
    void clearAvgValues() throws SQLException {
        LinkSQL ls = new LinkSQL();
        ls.clearAvgValues();
        double [] d1 = new double[]{4,3,5,9,6,3,2,1,5,7,2,6};
        ls.savePixelAveragesToDB(d1);
        ls.clearAvgValues();
        String sql = "select avg_pv from avg_values order by p_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ArrayList<Double> d2 = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            double p1 = rs.getDouble(1);
            d2.add(p1);
        }
        assertEquals(0,d2.size());
    }

    /**
     * Clears principle components table then saves new array to that table to ensure that it is populated. Then clears
     * again and runs result set. Verifies that nothing is returned.
     * @throws SQLException
     */
    @Test
    void clearPrincipleComponents() throws SQLException {
        LinkSQL ls = new LinkSQL();
        ls.clearPrincipleComponents();
        double [][] d1 = new double[][]{{4,3,5,9},{2,1,9,3},{8,6,5,4},{1,7,3,2}};
        ls.saveVectorsToDB(d1);
        ls.clearPrincipleComponents();
        String sql = "select pc_1,pc_2,pc_3,pc_4 from principle_components order by p_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ArrayList<Double> d2 = new ArrayList<>();
        ArrayList<Double> d3 = new ArrayList<>();
        ArrayList<Double> d4 = new ArrayList<>();
        ArrayList<Double> d5 = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            double p1 = rs.getDouble(1);
            d2.add(p1);
            double p2 = rs.getDouble(2);
            d3.add(p2);
            double p3 = rs.getDouble(3);
            d4.add(p3);
            double p4 = rs.getDouble(4);
            d5.add(p4);
        }
        assertEquals(0,d2.size());
        assertEquals(0,d3.size());
        assertEquals(0,d4.size());
        assertEquals(0,d5.size());
    }

    /**
     * Clears weights table then saves new array to that table to ensure that its populated. Then clears again and runs
     * result set. Verifies that nothing is returned.
     * @throws SQLException
     */
    @Test
    void clearWeights() throws SQLException {
        LinkSQL ls = new LinkSQL();
        ls.clearWeights();
        double [][] d1 = new double[][]{{4,3,5,9},{2,1,9,3},{8,6,5,4},{1,7,3,2}};
        ls.saveWeightsTableToDB(d1);
        ls.clearWeights();
        String sql = "select weight_1,weight_2,weight_3,weight_4 from weights order by image_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ArrayList<Double> d2 = new ArrayList<>();
        ArrayList<Double> d3 = new ArrayList<>();
        ArrayList<Double> d4 = new ArrayList<>();
        ArrayList<Double> d5 = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            double p1 = rs.getDouble(1);
            d2.add(p1);
            double p2 = rs.getDouble(2);
            d3.add(p2);
            double p3 = rs.getDouble(3);
            d4.add(p3);
            double p4 = rs.getDouble(4);
            d5.add(p4);
        }
        assertEquals(0,d2.size());
        assertEquals(0,d3.size());
        assertEquals(0,d4.size());
        assertEquals(0,d5.size());
    }
}