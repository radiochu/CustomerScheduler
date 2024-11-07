package DBAccess;


import DBConnection.JDBC;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

    /**
     * Gets all rows from the first_level_divisions table of the database and creates a Divisions object for each row.
     * The objects are then added to an ObservableList for use elsewhere in the application.
     *
     * @return the ObservableList divisions containing objects for all divisions.
     */
    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> divisions = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division, Country_id FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String divName = rs.getString(1);
                int countryID = rs.getInt(2);
                divisions.add(new Divisions(divName, countryID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }

    /**
     * Gets the division ID from the database that has a division name matching the value passed to the method and stores
     * the division_ID returned by the query into the divID variable.
     *
     * @return int divID containing the ID matching the division name.
     */
    public static int getDivisionIDByName(String divisionName) {
        int divID = 0;
        try {
            String sql = "SELECT division_id FROM first_level_divisions WHERE division = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, divisionName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                divID = rs.getInt("division_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divID;
    }
}