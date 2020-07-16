package ApplicationFiles;

import org.apache.commons.math3.linear.SingularValueDecomposition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreSingularValues {

    double [] d1;

    public void insertValues(String img_ID, SVD svd1) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","byb19169", "1234567899");
        SingularValueDecomposition inputArray = svd1.getTrainingSVD();
        d1 = inputArray.getSingularValues();
        PreparedStatement ps = conn.prepareStatement(
                "insert into singular_values (img_id, sv_1, sv_2, sv_3, sv_4, sv_5) values (?,?,?,?,?,?)"
        );
        ps.setString(1, img_ID);
        ps.setDouble(2, d1[0]);
        ps.setDouble(3, d1[1]);
        ps.setDouble(4, d1[2]);
        ps.setDouble(5, d1[3]);
        ps.setDouble(6, d1[4]);

        ps.executeUpdate();
        conn.close();
    }
}
