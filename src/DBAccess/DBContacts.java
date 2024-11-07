package DBAccess;

import DBConnection.JDBC;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

    /**
     * Gets all rows from the contacts table of the database and creates a Contacts object for each row.
     * The objects are then added to an ObservableList for use elsewhere in the application.
     *
     * @return the ObservableList contacts containing objects for all contacts.
     */
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_Name, contact_id FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            contacts.clear();
            while (rs.next()) {
                Contacts c = new Contacts(rs.getString(1), rs.getInt(2));
                contacts.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    /**
     * /**
     * Gets all contacts from the database that have a user_id matching the value passed to the method and creates
     * a Contacts object for each row. The objects are then added to an ObservableList for use elsewhere in the application.
     *
     * @return ObservableList c containing the objects for the retrieved list of contacts.
     */
    public static Contacts getContactByID(int apptContact) {
        String contactName = null;
        int contactID = 0;
        Contacts c = null;
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, apptContact);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contactName = rs.getString("Contact_Name");
                contactID = rs.getInt("contact_id");
                c = new Contacts(contactName, contactID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
