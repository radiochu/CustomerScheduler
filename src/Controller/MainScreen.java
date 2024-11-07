package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointments;
import Model.Customers;
import Utilities.Alerts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controller that handles the logic for the main screen based on mainScreen.fxml.
 */
public class MainScreen implements Initializable {

    static ObservableList<Customers> customers = DBCustomers.getAllCustomers();
    static ObservableList<Appointments> appointments = DBAppointments.getAllAppointments();
    static ObservableList<Appointments> currMonthAppts = DBAppointments.getThisMonthAppts();
    static ObservableList<Appointments> currWeekAppts = DBAppointments.getThisWeekAppts();
    public TableView<Customers> allCustomers;
    public TableColumn<Customers, Integer> custID;
    public TableColumn<Customers, String> custName;
    public TableColumn<Customers, String> custAddress;
    public TableColumn<Customers, String> custFLD;
    public TableColumn<Customers, String> custPostCode;
    public TableColumn<Customers, String> custCountry;
    public TableColumn<Customers, String> custPhone;
    public Button custAdd;
    public Button custModify;
    public Button custDelete;
    public Tab allApptTab;
    public TableView<Appointments> allAppointments;
    public TableColumn<Appointments, Integer> allApptID;
    public TableColumn<Appointments, String> allApptTitle;
    public TableColumn<Appointments, String> allApptDesc;
    public TableColumn<Appointments, String> allApptLoc;
    public TableColumn<Appointments, String> allApptContact;
    public TableColumn<Appointments, String> allApptType;
    public TableColumn<Appointments, LocalDateTime> allApptStarts;
    public TableColumn<Appointments, LocalDateTime> allApptEnds;
    public TableColumn<Appointments, Integer> allApptCustID;
    public TableColumn<Appointments, Integer> allApptUserID;
    public Tab currentMonthAppts;
    public TableView<Appointments> currMonthApptsTable;
    public TableColumn<Appointments, Integer> currMonthApptID;
    public TableColumn<Appointments, String> currMonthApptTitle;
    public TableColumn<Appointments, String> currMonthApptDesc;
    public TableColumn<Appointments, String> currMonthApptLoc;
    public TableColumn<Appointments, String> currMonthApptContact;
    public TableColumn<Appointments, String> currMonthApptType;
    public TableColumn<Appointments, LocalDateTime> currMonthApptStarts;
    public TableColumn<Appointments, LocalDateTime> currMonthApptEnds;
    public TableColumn<Appointments, Integer> currMonthApptCustID;
    public TableColumn<Appointments, Integer> currMonthApptUserID;
    public Tab currentWeekAppts;
    public TableView<Appointments> currWeekApptsTable;
    public TableColumn<Appointments, Integer> currWeekApptID;
    public TableColumn<Appointments, String> currWeekApptTitle;
    public TableColumn<Appointments, String> currWeekApptDesc;
    public TableColumn<Appointments, String> currWeekApptLoc;
    public TableColumn<Appointments, String> currWeekApptContact;
    public TableColumn<Appointments, String> currWeekApptType;
    public TableColumn<Appointments, LocalDateTime> currWeekApptStarts;
    public TableColumn<Appointments, LocalDateTime> currWeekApptEnds;
    public TableColumn<Appointments, Integer> currWeekApptCustID;
    public TableColumn<Appointments, Integer> currWeekApptUserID;
    public Button apptAdd;
    public Button apptModify;
    public Button apptDelete;
    public Button reportsButton;
    public Button exitButton;

    /**
     * Initializes the main screen. Sets up factories for cell and property values to pull the correct properties
     * from each object and display them in the tableview.
     *
     * @param url - not used
     * @param resourceBundle - not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custID.setCellValueFactory(new PropertyValueFactory<>("id"));
        custName.setCellValueFactory(new PropertyValueFactory<>("name"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        custFLD.setCellValueFactory(new PropertyValueFactory<>("fld"));
        custPostCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        custCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        allCustomers.setItems(customers);

        allApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        allApptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        allApptDesc.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        allApptLoc.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        allApptContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        allApptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        allApptStarts.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        allApptEnds.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        allApptCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        allApptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        allAppointments.setItems(appointments);

        currMonthApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        currMonthApptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        currMonthApptDesc.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        currMonthApptLoc.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        currMonthApptContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        currMonthApptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        currMonthApptStarts.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        currMonthApptEnds.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        currMonthApptCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        currMonthApptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        currMonthApptsTable.setItems(currMonthAppts);

        currWeekApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        currWeekApptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        currWeekApptDesc.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        currWeekApptLoc.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        currWeekApptContact.setCellValueFactory(new PropertyValueFactory<>("apptContact"));
        currWeekApptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        currWeekApptStarts.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        currWeekApptEnds.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        currWeekApptCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));
        currWeekApptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        currWeekApptsTable.setItems(currWeekAppts);
    }

    /**
     * Method used to reload all tables on the main screen with updated information pulled from the database.
     */
    public void refreshTables() {
        customers.setAll(DBCustomers.getAllCustomers());
        appointments.setAll(DBAppointments.getAllAppointments());
        currMonthAppts.setAll(DBAppointments.getThisMonthAppts());
        currWeekAppts.setAll(DBAppointments.getThisWeekAppts());
    }

    /**
     * Handles loading the Add Customer screen when the add button is pressed on the customer menu.
     *
     * @param actionEvent - not used
     * @throws IOException - not used
     */
    public void onCustAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage overlay = new Stage();
        overlay.setTitle("Add Customer");
        overlay.setScene(new Scene(root, 400, 400));
        overlay.show();
    }

    /**
     * Handles loading the Modify Customer screen when the modify button is pressed on the customer menu.
     *
     * @param actionEvent - not used
     * @throws IOException - not used
     */
    public void onCustModify(ActionEvent actionEvent) throws IOException {
        Customers customerToMod = allCustomers.getSelectionModel().getSelectedItem();
        int indexToMod = allCustomers.getSelectionModel().getSelectedIndex();
        if (customerToMod != null) {
            ModifyCustomer.setCustomerToMod(customerToMod, indexToMod);
            Parent root = FXMLLoader.load(getClass().getResource("/view/modifyCustomer.fxml"));
            Stage overlay = new Stage();
            overlay.setTitle("Modify Customer");
            overlay.setScene(new Scene(root, 400, 400));
            overlay.show();
        } else {
            Alerts.noSelection();
        }
    }

    /**
     * Handles the delete button being pressed on the customer menu.
     * Also provides for deleting any appointments belonging to the selected customer.
     *
     * @param actionEvent - not used
     */
    public void onCustDelete(ActionEvent actionEvent) {
        int custToDelete = allCustomers.getSelectionModel().getSelectedItem().getId();
        if (Alerts.deleteCustomer()) {
            if (DBAppointments.getApptsByCustID(custToDelete).size() != 0) {
                DBAppointments.deleteApptsByCust(custToDelete);
            }
        }
        if (DBCustomers.deleteCustomer(custToDelete)) {
            Alerts.customerDeleted();
            refreshTables();
        }
    }


    /**
     * Handles loading the Add Appointment screen when the add button is pressed on the appointment menu.
     *
     * @param actionEvent - not used
     * @throws IOException - not used
     */
    public void onApptAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        Stage overlay = new Stage();
        overlay.setTitle("Add Appointment");
        overlay.setScene(new Scene(root, 560, 400));
        overlay.show();
    }

    /**
     * Handles loading the Modify Appointment screen when the modify button is pressed on the appointment menu.
     *
     * @param actionEvent - not used
     * @throws IOException - not used
     */
    public void onApptModify(ActionEvent actionEvent) throws IOException {
        Appointments appt;
        int indexToMod = 0;
        if (allApptTab.isSelected()) {
            appt = allAppointments.getSelectionModel().getSelectedItem();
            indexToMod = allAppointments.getSelectionModel().getSelectedIndex();
        } else if (currentMonthAppts.isSelected()) {
            appt = currMonthApptsTable.getSelectionModel().getSelectedItem();
            indexToMod = currMonthApptsTable.getSelectionModel().getSelectedIndex();
        } else {
            appt = currWeekApptsTable.getSelectionModel().getSelectedItem();
            indexToMod = currWeekApptsTable.getSelectionModel().getSelectedIndex();
        }
        if (appt == null) {
            Alerts.noSelection();
        } else {
            ModifyAppointment.setAppointmentToMod(appt, indexToMod);
            Parent root = FXMLLoader.load(getClass().getResource("/view/modifyAppointment.fxml"));
            Stage overlay = new Stage();
            overlay.setTitle("Modify Appointment");
            overlay.setScene(new Scene(root, 560, 400));
            overlay.show();
        }
    }

    /**
     * Handles the delete button being pressed on the appointment menu with a conditional check for
     * which tab is currently selected. Afterwards, calls refreshTables() to be sure all tables are updated.
     *
     * @param actionEvent - not used
     */
    public void onApptDelete(ActionEvent actionEvent) {
        Appointments appt;
        String apptType;
        if (allApptTab.isSelected()) {
            appt = allAppointments.getSelectionModel().getSelectedItem();
            apptType = allAppointments.getSelectionModel().getSelectedItem().getApptType();
        } else if (currentMonthAppts.isSelected()) {
            appt = currMonthApptsTable.getSelectionModel().getSelectedItem();
            apptType = currMonthApptsTable.getSelectionModel().getSelectedItem().getApptType();
        } else {
            appt = currWeekApptsTable.getSelectionModel().getSelectedItem();
            apptType = currWeekApptsTable.getSelectionModel().getSelectedItem().getApptType();
        }
        if (appt == null) {
            Alerts.noSelection();
        } else {
            int apptToDelete = appt.getApptID();
            {
                if (Alerts.deleteAppointment()) {
                    if (DBAppointments.deleteAppointment(apptToDelete)) {
                        Alerts.apptDeleted(apptToDelete, apptType);
                        refreshTables();
                    }

                }
            }
        }
    }

    /**
     * Handles loading the Reports screen when the reports button is pressed on the appointment menu.
     *
     * @param actionEvent - not used
     * @throws IOException - not used
     */
    public void viewReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reporter.fxml"));
        Stage overlay = new Stage();
        overlay.setTitle("Add Appointment");
        overlay.setScene(new Scene(root, 715, 466));
        overlay.show();
    }

    /**
     * Confirms that the user wants to close the application by providing an alert from the Alerts class.
     *
     * @param actionEvent - not used
     */
    public void onExitButton(ActionEvent actionEvent) {
        Alerts.exitApplication();
    }


}