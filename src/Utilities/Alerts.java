package Utilities;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class Alerts {

    /**
     * Alert a that is used as a base on which to create the custom alerts used throughout the application.
     */
    public static final Alert a = new Alert(Alert.AlertType.NONE);

    /**
     * Alerts user of upcoming appointments.
     *
     * @param upcoming Number of upcoming appointments as provided by upcomingAppointments function in the Login class.
     */
    public static void upcomingAppointments(int upcoming, String nextAppointment) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(upcoming + " upcoming appointment(s) in the next 15 minutes.\n" + nextAppointment);
        a.show();
    }

    /**
     * Alerts user of invalid data.
     *
     * @param message The detailed error message to be displayed as provided by the screen that has invalid data.
     */
    public static void invalidData(String message) {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("You have entered invalid data: " + message + "Please check your entries and try again.");
        a.show();
    }

    /**
     * Confirms with user that they want to delete the customer as well as any scheduled appointments that customer may have.
     *
     * @return boolean - true if user presses OK in the dialog box, false if they press cancel
     */
    public static Boolean deleteCustomer() {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Are you sure you want to delete the selected customer?\nNote: This will also delete any appointments scheduled\n for this customer.");
        Optional<ButtonType> result = a.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /**
     * Alerts a user that a customer has existing appointments.
     */
    public static void existingAppts() {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("You cannot delete a customer with scheduled appointments.\nPlease delete any existing appointments before deleting\nthis customer.");
        a.show();
    }

    /**
     * Alerts customer they are attempting to perform an action that requires a selection without anything selected.
     */
    public static void noSelection() {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("Nothing selected! Please make a selection first.");
        a.show();
    }

    /**
     * Notifies user that customer has been deleted.
     */
    public static void customerDeleted() {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText("Selected customer has been deleted.");
        a.show();
    }

    /**
     * Confirms with user that they want to delete the selected appointment.
     *
     * @return boolean - true if user presses OK in the dialog box, false if they press cancel
     */
    public static Boolean deleteAppointment() {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Are you sure you want to delete the selected appointment?");
        Optional<ButtonType> result = a.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /**
     * Confirms with user that they want to close out of the screen they are in without saving any data they have input.
     *
     * @param actionEvent Used to identify the window from which the save was triggered to close it on a successful execution.
     */
    public static void cancelWithoutSaving(ActionEvent actionEvent) {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Quit without saving changes?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Confirms if user wants to exit the application and exits if the user chooses to do so.
     */
    public static void exitApplication() {
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**
     * Confirms appointment has been deleted and displays appointment ID and type.
     *
     * @param apptToDelete the ID for the appointment to delete - int
     * @param apptType     the appointment type - String
     */
    public static void apptDeleted(int apptToDelete, String apptType) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText("Appointment " + apptToDelete + " of type " + apptType + " has been deleted.");
        a.show();
    }

    /**
     * Alerts user that the appointment they are attempting to create has time overlaps with pre-existing appointments
     * for the chosen customer.
     */
    public static void timeOverlap() {
        a.setAlertType(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("The proposed appointment overlaps an existing appointment\n for this customer. Please choose another time.");
        a.show();
    }
}