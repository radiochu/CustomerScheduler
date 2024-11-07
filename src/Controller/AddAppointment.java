package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import Model.Contacts;
import Utilities.Alerts;
import Utilities.DateTimeHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


/**
 * Controller that handles the logic for the Add Appointment screen based on addAppointment.fxml.
 */
public class AddAppointment implements Initializable {
    public TextField apptID;
    public TextField apptCustID;
    public ComboBox<Integer> apptUserID;
    public TextField apptTitle;
    public TextField apptDesc;
    public TextField apptType;
    public TextField apptLoc;
    public ComboBox<Contacts> apptContact;
    public DatePicker apptDate;
    public ComboBox<LocalTime> apptStartTime;
    public ComboBox<LocalTime> apptEndTime;
    public Button Submit;
    public Button Cancel;

    ObservableList<Integer> allUsers = DBUsers.getAllUsers();
    ObservableList<Contacts> allContacts = DBContacts.getAllContacts();

    /**
     * Initializes the Add Appointment window and sets initial values for all fields.
     *
     * @param url - not used
     * @param resourceBundle - not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptContact.setItems(allContacts);
        apptUserID.setItems(allUsers);
        apptDate.setValue(LocalDate.now());
        apptStartTime.setItems(DateTimeHandler.setTimeList(apptDate.getValue()));
        apptEndTime.setItems(DateTimeHandler.setTimeList(apptDate.getValue()));
    }

    /**
     * Validates the data entered into the add appointment fields. If any field is found invalid, the user is alerted
     * via a custom notification from the Alerts class, populated with the provided string to explain the error.
     *
     * @return boolean b; false if information is not valid, true if it is.
     */
    private boolean validateInput() {
        boolean b = false;

        if (apptCustID.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer ID.\n");
        } else if (apptUserID.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a user.\n");
        } else if (apptTitle.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment title.\n");
        } else if (apptDesc.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment description.\n");
        } else if (apptType.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment type.\n");
        } else if (apptLoc.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input an appointment location.\n");
        } else if (apptDate.getValue() == null) {
            Alerts.invalidData("\nYou must choose an appointment date.\n");
        } else if (apptStartTime.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a start time.\n");
        } else if (apptEndTime.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose an end time.\n");
        } else if (apptContact.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a contact.\n");
        } else {
            b = true;
        }
        return b;
    }

    /**
     * Validates times chosen for appointment start and end do not overlap. Generates an alert if overlap is found.
     *
     * @return boolean b - false if overlaps found, true if no overlaps
     */
    private boolean validateTime() {
        boolean b = true;
        ObservableList<Appointments> custAppts = DBAppointments.getApptsByCustID(Integer.parseInt(apptCustID.getText()));
        for (Appointments i : custAppts) {
            LocalDateTime aStart = i.getApptStart();
            LocalDateTime aEnd = i.getApptEnd();
            LocalDateTime bStart = LocalDateTime.of(apptDate.getValue(), apptStartTime.getValue());
            LocalDateTime bEnd = LocalDateTime.of(apptDate.getValue(), apptEndTime.getValue());
            if (aStart.isEqual(bStart) || aStart.isAfter(bStart) && aStart.isBefore(bEnd)) {
                Alerts.timeOverlap();
                b = false;
            } else if (aEnd.isEqual(bEnd) || aEnd.isBefore(bEnd) && aEnd.isAfter(bStart)) {
                Alerts.timeOverlap();
                b = false;
            } else if ((bStart.isAfter(aStart) && bEnd.isBefore(aEnd)) || (aStart.isEqual(bStart) && aEnd.isEqual(bEnd))) {
                Alerts.timeOverlap();
                b = false;
            }
            else if (bStart.isEqual(bEnd)) {
                Alerts.invalidData("\nStart time cannot be the same as end time.\n");
                b = false;
            }
        }
        return b;
    }

    /**
     * Checks to see if the customer entered for the appointment actually exists. Provides an alert
     * from the Alerts class if the customer does not exist.
     *
     * INCLUDES LAMBDA - streams all customers list, checking each to see if any ID is a match for the chosen ID.
     * This is more concise than running the same comparison using a for loop.
     *
     * @return boolean b; returns false if customer does not exist, true if the customer is found.
     */
    private boolean validateCustomer() {
        boolean b = DBCustomers.getAllCustomers().stream().anyMatch(s -> s.getId() == Integer.parseInt(apptCustID.getText()));
        if (!b) {
            Alerts.invalidData("\nThe chosen customer does not exist.\n");
        }
        return b;
    }

    /**
     * Method to handle saving the new appointment.
     *
     * @param actionEvent Used to identify the window from which the save was triggered to close it on a successful execution.
     */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateInput() && (validateCustomer() && validateTime())) {
            LocalDate date = apptDate.getValue();
            LocalTime startTime = LocalTime.from(apptStartTime.getValue());
            LocalTime endTime = LocalTime.from(apptEndTime.getValue());
            LocalDateTime startLDT = DateTimeHandler.startTime(date, startTime);
            LocalDateTime endLDT = DateTimeHandler.endTime(date, endTime);
            Appointments apptToAdd = new Appointments(0, apptTitle.getText(), apptDesc.getText(), apptLoc.getText(), apptContact.getValue(), startLDT, endLDT, apptType.getText(), Integer.parseInt(apptCustID.getText()), apptUserID.getValue());
            apptToAdd.setApptID(DBAppointments.addAppointment(apptToAdd));
            MainScreen.appointments.add(apptToAdd);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Confirms that the user wants to cancel adding the new appointment by providing an alert from the Alerts class.
     *
     * @param actionEvent Passed to a method in the Alerts class; used to close the window if the user confirms they want
     * to close without saving.
     */
    public void onCancel(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
