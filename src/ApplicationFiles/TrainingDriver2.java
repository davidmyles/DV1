package ApplicationFiles;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class TrainingDriver2 {

    static double[] d1a;
    static double[] d2a;
    static double[] d3a;
    static RandomImage ric1;
    static RandomImage ric2;
    static RandomImage ric3;

    public static void runImageDriver2() throws SQLException {
        RandomImage ric1 = new RandomImage();
        ric1.createArray();
        double[][] d1 = ric1.getPixelArray();
        System.out.println(Arrays.deepToString(d1));
        ric1.flattenArray(d1);
        d1a = ric1.getFlatArray();
        //System.out.println(Arrays.toString(d1a));
        ric1.createImage(d1);
        File file1 = new File("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_01.png");
        try {
            ImageIO.write(ric1.newImage, "png", file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomImage ric2 = new RandomImage();
        ric2.createArray();
        double[][] d2 = ric2.getPixelArray();
        System.out.println(Arrays.deepToString(d2));
        ric2.flattenArray(d2);
        d2a = ric2.getFlatArray();
        //System.out.println(Arrays.toString(d2));
        ric2.createImage(d2);
        File file2 = new File("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_02.png");
        try {
            ImageIO.write(ric2.newImage, "png", file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomImage ric3 = new RandomImage();
        ric3.createArray();
        double[][] d3 = ric3.getPixelArray();
        System.out.println(Arrays.deepToString(d3));
        ric3.flattenArray(d3);
        d3a = ric3.getFlatArray();
        //System.out.println(Arrays.toString(d3a));
        ric3.createImage(d3);
        File file3 = new File("/Users/davidjmyles/IdeaProjects/DV1/trainingimages1/img_03.png");
        try {
            ImageIO.write(ric3.newImage, "png", file3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void something(double[] d1a, double[] d2a, double[] d3a) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps1 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps1.setString(1, "p1");
        ps1.setDouble(2, d1a[0]);
        ps1.setDouble(3, d2a[0]);
        ps1.setDouble(4, d3a[0]);
        ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps2.setString(1, "p2");
        ps2.setDouble(2, d1a[1]);
        ps2.setDouble(3, d2a[1]);
        ps2.setDouble(4, d3a[1]);
        ps2.executeUpdate();
        PreparedStatement ps3 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps3.setString(1, "p3");
        ps3.setDouble(2, d1a[2]);
        ps3.setDouble(3, d2a[2]);
        ps3.setDouble(4, d3a[2]);
        ps3.executeUpdate();
        PreparedStatement ps4 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps4.setString(1, "p4");
        ps4.setDouble(2, d1a[3]);
        ps4.setDouble(3, d2a[3]);
        ps4.setDouble(4, d3a[3]);
        ps4.executeUpdate();
        PreparedStatement ps5 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps5.setString(1, "p5");
        ps5.setDouble(2, d1a[4]);
        ps5.setDouble(3, d2a[4]);
        ps5.setDouble(4, d3a[4]);
        ps5.executeUpdate();
        PreparedStatement ps6 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps6.setString(1, "p6");
        ps6.setDouble(2, d1a[5]);
        ps6.setDouble(3, d2a[5]);
        ps6.setDouble(4, d3a[5]);
        ps6.executeUpdate();
        PreparedStatement ps7 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps7.setString(1, "p7");
        ps7.setDouble(2, d1a[6]);
        ps7.setDouble(3, d2a[6]);
        ps7.setDouble(4, d3a[6]);
        ps7.executeUpdate();
        PreparedStatement ps8 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps8.setString(1, "p8");
        ps8.setDouble(2, d1a[7]);
        ps8.setDouble(3, d2a[7]);
        ps8.setDouble(4, d3a[7]);
        ps8.executeUpdate();
        PreparedStatement ps9 = conn.prepareStatement("insert into training_images1 (pixel_id, img_1, img_2, img_3) values (?,?,?,?)");
        ps9.setString(1, "p9");
        ps9.setDouble(2, d1a[8]);
        ps9.setDouble(3, d2a[8]);
        ps9.setDouble(4, d3a[8]);
        ps9.executeUpdate();
        conn.close();
    }

    public static void main(String args[]) throws IOException, SQLException, ClassNotFoundException {
        runImageDriver2();
        something(d1a,d2a,d3a);
    }
}



