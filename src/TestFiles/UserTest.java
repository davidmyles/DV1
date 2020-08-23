package TestFiles;

import ApplicationFiles.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for User objects
 */
class UserTest {

    /**
     * Creates user using 4 argument constructor. Adds details to the database and then changes fields to alternative
     * entries. Retrieves from databse to reset fields. Test verifies that details from database match original fields
     * @throws SQLException
     */
    @Test
    void addUserToDB() throws SQLException {
        String constructorUserID = "user_61";
        String constructorFName = "fname61";
        String constructorLName = "lname_61";
        int constructorImageID = 61;
        User u = new User(constructorUserID,constructorFName,constructorLName,constructorImageID);
        u.deleteUserFromDB("user_61");
        u.addUserToDB();
        u.setUserID("x");
        u.setfName("y");
        u.setlName("z");
        u.setImageID(300);
        u.retrieveUserDetails("user_61");
        assertEquals(constructorUserID, u.getUserID());
        assertEquals(constructorFName, u.getfName());
        assertEquals(constructorLName, u.getlName());
        assertEquals(constructorImageID, u.getImageID());
    }

    /**
     * Creates user object using no argument constructor. Creates set of user details and adds to database. Details then
     * retrieved, and test verifies that the two sets match
     * @throws SQLException
     */
    @Test
    void retrieveUserDetails() throws SQLException {
        User u = new User();
        u.deleteUserFromDB("user_61");
        String dbUserID = "user_61";
        u.setUserID(dbUserID);
        String dbFName = "fname61";
        u.setfName(dbFName);
        String dbLName = "lname61";
        u.setlName(dbLName);
        int dbImageID = 61;
        u.setImageID(dbImageID);
        u.addUserToDB();
        u.retrieveUserDetails("user_61");
        assertEquals(dbUserID, u.getUserID());
        assertEquals(dbFName, u.getfName());
        assertEquals(dbLName, u.getlName());
        assertEquals(dbImageID, u.getImageID());
    }

    /**
     * Creates new User and a set of details. Matching user details then retrieved from database. This database entry is
     * then deleted. Creates new user object and attempts to retrieve the same details. Test verifies that all fields
     * user in question are now reset to default
     * @throws SQLException
     */
    @Test
    void deleteUserFromDB() throws SQLException {
        User u = new User();
        String dbUserID = "user_61";
        String dbFName = "fname61";
        String dbLName = "lname61";
        int dbImageID = 61;
        u.retrieveUserDetails("user_61");
        assertEquals(dbUserID, u.getUserID());
        assertEquals(dbFName, u.getfName());
        assertEquals(dbLName, u.getlName());
        assertEquals(dbImageID, u.getImageID());
        u.deleteUserFromDB("user_61");
        User u2 = new User();
        u2.retrieveUserDetails("user_61");
        assertEquals("not set", u2.getUserID());
        assertEquals("not set", u2.getfName());
        assertEquals("not set", u2.getlName());
        assertEquals(0, u2.getImageID());
    }

    /**
     * Creates 2 new users with completed details added to database. All users then deleted from database. Creates 2 new
     * User objects and tries to retrieve original 2 users. Test verifies that details are no longer present in the
     * database
     * @throws SQLException
     */
    @Test
    void deleteAllUsersFromDB() throws SQLException {
        User u1 = new User("user_61","fname61","lname61",61);
        User u2 = new User("user_62","fname62","lname62",62);
        User u3 = new User();
        u3.deleteAllUsersFromDB();
        User u4 = new User();
        u4.retrieveUserDetails("user_61");
        assertEquals("not set", u4.getUserID());
        assertEquals("not set", u4.getfName());
        assertEquals("not set", u4.getlName());
        assertEquals(0, u4.getImageID());
        User u5 = new User();
        u5.retrieveUserDetails("user_62");
        assertEquals("not set", u5.getUserID());
        assertEquals("not set", u5.getfName());
        assertEquals("not set", u5.getlName());
        assertEquals(0, u5.getImageID());
    }
}