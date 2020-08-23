package ApplicationFiles;

import java.sql.*;

/**
 * Class which creates user objects
 */
public class User {

    private String userID;
    private String fName;
    private String lName;
    private int imageID;

    /**
     * Constructor with 4 arguments. Used for creating a new system user
     * @param userID - users userid
     * @param fName - users first name
     * @param lName - users last name
     * @param imageID - id of users biometric template. Matches that stored in database weights table
     */
    public User(String userID, String fName, String lName, int imageID)   {
        this.userID = userID;
        this.fName = fName;
        this.lName = lName;
        this.imageID = imageID;
    }

    /**
     * Zero argument constructor. Used when retrieving user details from databse.
     */
    public User()   {
        this.userID = "not set";
        this.fName = "not set";
        this.lName = "not set";
        this.imageID = 0;
    }

    /**
     * Adds new set of user details to the database. Details retrieved from user object fields
     * @throws SQLException
     */
    public void addUserToDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("insert into users (user_id,f_name,l_name,image_id) values (?,?,?,?)");
            ps.setString(1, this.userID);
            ps.setString(2, this.fName);
            ps.setString(3, this.lName);
            ps.setInt(4, this.imageID);
            ps.executeUpdate();
        conn.close();
    }

    /**
     * Retrieves user details from database with userID as the parameter. Sets retrieved details to corresponding fields
     * @param userID - user to be retrieved.
     * @throws SQLException
     */
    public void retrieveUserDetails(String userID) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("select user_id,f_name,l_name,image_id from users where user_id = ?");
        ps.setString(1, userID);
        ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("user_id");
                setUserID(a);
                String b = rs.getString("f_name");
                setfName(b);
                String c = rs.getString("l_name");
                setlName(c);
                int d = rs.getInt("image_id");
                setImageID(d);
        }
        conn.close();
    }

    /**
     * Deletes specified users details from the database
     * @param userID - user to be deleted
     * @throws SQLException
     */
    public void deleteUserFromDB(String userID) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("delete from users where user_id = ?");
        ps.setString(1, userID);
        ps.executeQuery();
        conn.close();
    }

    /**
     * Deletes all users from users table in Database
     * @throws SQLException
     */
    public void deleteAllUsersFromDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "byb19169", "1234567899");
        PreparedStatement ps = conn.prepareStatement("delete from users");
        ps.executeQuery();
        conn.close();
    }

    /**
     * Accessor for userID
     * @return - userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Mutator for userID
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Accessor for first name
     * @return - fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * Mutator for first name
     * @param fName
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * Accessor for last name
     * @return - lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * Mutator for last name
     * @param lName
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * Accessor for imageID
     * @return - imageID
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * Mutator for imageID
     * @param imageID
     */
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
