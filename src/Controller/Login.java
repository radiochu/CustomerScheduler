package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import DBConnection.JDBC;
import Model.Appointments;
import Utilities.Alerts;
import Utilities.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Controller that handles the logic for the Login screen based on login.fxml.
 */
public class Login implements Initializable {
    /**
     * Username field.
     */
    public static String uname;
    /**
     * An alert used for displaying errors with login.
     */
    public Alert a = new Alert(Alert.AlertType.NONE);
    /**
     * Label to display user's current time zone.
     */
    public Label timezone;
    /**
     * Password field.
     */
    public PasswordField password;
    /**
     * The Username.
     */
    public TextField username;
    /**
     * Resource bundle used for localization of the login screen.
     */
    public ResourceBundle resourceBundle;

    /**
     * Initializes the Login window and sets initial values for all fields.
     *
     * @param url - not used
     * @param resourceBundle - used to specify the resource bundle to use for localization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TimeZone tz = TimeZone.getDefault();
        timezone.setText(resourceBundle.getString("timezone") + ":\n" + tz.getDisplayName());
        this.resourceBundle = resourceBundle;
    }

    /**
     * Method to handle user login. First, username and password are validated against the database.
     * The login attempt and whether it was successful or unsuccessful is sent to the Logger to be written
     * to a log file. If the login was successful, loads and moves to the main screen of the application.
     *
     * @param actionEvent Used to identify the window from which the login was triggered to close it on a successful execution.
     * @throws IOException  Thrown if exceptions occur writing to log file
     * @throws SQLException Thrown if exceptions occur during SQL queries
     */

    public void onSubmit(ActionEvent actionEvent) throws IOException, SQLException {
        uname = username.getText();
        String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, password.getText());
        ps.setString(2, username.getText());
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setHeaderText(null);
            a.setContentText(resourceBundle.getString("loginfail"));
            a.show();
            Logger.logActivity("Login by user " + uname + " was unsuccessful at UTC" + Instant.now());
        } else {
            Logger.logActivity("Login by user " + uname + " was successful at UTC " + Instant.now());
            Stage previous = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            previous.close();
            Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Customer Scheduling System");
            mainStage.setScene(new Scene(root, 1020, 750));
            mainStage.show();
            upcomingAppointments(DBUsers.getUserIDByName(uname));
        }
    }

    /**
     * Method to display upcoming appointments within 15 minutes past the corresponding user's login.
     * @param user The user to scan for appointments for.
     */
    public void upcomingAppointments(int user) {
        ObservableList<Appointments> userAppointments = DBAppointments.getApptsByUserID(user);
        ObservableList<Appointments> upcomingAppts = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in15Minutes = now.plusMinutes(15);
        for (Appointments a : userAppointments) {
            LocalDateTime apptTime = a.getApptStart();
            if (apptTime.isAfter(now.minusMinutes(1)) && apptTime.isBefore(in15Minutes)) {
                upcomingAppts.add(a);
            }
        }
        String nextAppointment = null;
        if (upcomingAppts.size() != 0) {
            nextAppointment = "\nNext appointment:\nAppointment ID " + upcomingAppts.get(0).getApptID() + " of type " + upcomingAppts.get(0).getApptType() + " at " + upcomingAppts.get(0).getApptStart();
        }
        else {
            nextAppointment = " ";
        }
        Alerts.upcomingAppointments(upcomingAppts.size(), nextAppointment);
    }

    /**
     * Confirms that the user wants to cancel login and close the application by providing an alert from the Alerts class.
     *
     * @param actionEvent - not used
     */
    public void onCancel(ActionEvent actionEvent) {
        Alerts.exitApplication();
    }
}
