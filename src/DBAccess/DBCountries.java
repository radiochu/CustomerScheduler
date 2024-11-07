package DBAccess;

import DBConnection.JDBC;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {

    /**
     * Gets all rows from the countries table of the database and creates a Countries object for each row.
     * The objects are then added to an ObservableList for use elsewhere in the application.
     *
     * @return the ObservableList countries containing objects for all countries.
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country, country_id FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String countryName = rs.getString(1);
                int countryID = rs.getInt(2);
                countries.add(new Countries(countryName, countryID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }
}
