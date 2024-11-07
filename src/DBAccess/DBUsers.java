package DBAccess;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {

    /**
     * Gets all rows from the users table of the database and creates a list containing the user_id for each row.
     * The IDs are then added to an ObservableList for use elsewhere in the application.
     *
     * @return the ObservableList userIDs containing user_ids for all users.
     */
    public static ObservableList<Integer> getAllUsers() {
        ObservableList<Integer> userIDs = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                userIDs.add(userID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIDs;
    }

    /**
     * Gets the user ID from the database that has a username matching the value passed to the method and stores
     * the user_id returned by the query into the userID variable.
     *
     * @return int userID containing the ID matching the username.
     */
    public static int getUserIDByName(String username) {
        int userID = 0;
        try {
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userID = rs.getInt("User_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }
}