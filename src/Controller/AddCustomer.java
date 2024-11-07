package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import Utilities.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller that handles the logic for the Add Customer screen based on addCustomer.fxml.
 */
public class AddCustomer implements Initializable {
    public TextField custNameField;
    public TextField custAddressField;
    public ComboBox<Countries> custCountryCB;
    public ComboBox<Divisions> custFLDCB;
    public TextField custPostCodeField;
    public TextField custPhoneField;
    public TextField customerID;
    public Button addCustSaveBtn;
    public Button addCustCancelBtn;
    public Label divisionLabel;

    /**
     * Initializes the Add Customer window and sets initial values for all fields.
     *
     * @param url - not used
     * @param resourceBundle - not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custCountryCB.setItems(DBCountries.getAllCountries());
    }

    /**
     * Validates the data entered into the add customer fields. If any field is found invalid, the user is alerted
     * via a custom notification from the Alerts class, populated with the provided string to explain the error.
     *
     * @return boolean b; false if information is not valid, true if it is.
     */
    private boolean validateInput() {
        boolean b = false;
        if (custNameField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer name.\n");
        } else if (custAddressField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer address.\n");
        } else if (custCountryCB.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a country.\n");
        } else if (custFLDCB.getSelectionModel().isSelected(-1)) {
            Alerts.invalidData("\nYou must choose a first-level division.\n");
        } else if (custPhoneField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer phone number.\n");
        } else if (custPostCodeField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer post code.\n");
        } else {
            b = true;
        }
        return b;
    }

    /**
     * Called when country combo box is changed to populate the first-level division combo box with the appropriate divisions.
     *
     * INCLUDES LAMBDA - streams all Divisions, filtering them by divisions that contain country IDs matching what the country
     * combo box is currently set to. This reduces the amount of code needed versus the way I initially implemented this
     * function, which was by using multiple if statements to compare whether the country matched one of the three possible.
     * This also allows for code not to require updating if more countries are added in the future.
     *
     * @param actionEvent - not used
     */
    public void filterDivisions(ActionEvent actionEvent) {
        ObservableList<Divisions> allDivisions = DBDivisions.getAllDivisions();
        custFLDCB.setItems(allDivisions.stream()
               .filter(x -> x.getCountryID() == (custCountryCB.getValue().getCountryID()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    /**
     * Method to handle saving the new customer.
     *
     * @param actionEvent Used to identify the window from which the save was triggered to close it on a successful execution.
     */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateInput()) {
            Customers customer = new Customers(0, custNameField.getText(), custAddressField.getText(), custFLDCB.getValue(), custPostCodeField.getText(), custCountryCB.getValue(), custPhoneField.getText());
            int newCustID = DBCustomers.addCustomer(customer);
            customer.setId(newCustID);
            MainScreen.customers.add(customer);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Confirms that the user wants to cancel adding the new customer by providing an alert from the Alerts class.
     *
     * @param actionEvent Passed to a method in the Alerts class; used to close the window if the user confirms they want
     * to close without saving.
     */
    public void onCancelButton(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
