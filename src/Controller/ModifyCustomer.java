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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller that handles the logic for the Modify Customer screen based on modifyCustomer.fxml.
 */
public class ModifyCustomer implements Initializable {
    public static Customers customerToMod = null;
    public static int indexToMod = 0;
    public TextField custNameField;
    public TextField custAddressField;
    public ComboBox<Countries> custCountryCB;
    public ComboBox<Divisions> custFLDCB;
    public TextField custPostCodeField;
    public TextField custPhoneField;
    public TextField customerID;
    public Button modCustSaveBtn;
    public Button modCustCancelBtn;
    public Label divisionLabel;

    /**
     * Sets customer to mod, pulled from the main screen controller.
     *
     * @param customer the customer chosen from main screen to modify.
     * @param index       the index of the customer in the list of all customers.
     */
    public static void setCustomerToMod(Customers customer, int index) {
        customerToMod = customer;
        indexToMod = index;
    }

    /**
     * Initializes the Modify Customer window and sets initial values for all fields.
     *
     * @param url - not used
     * @param resourceBundle - not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custNameField.setText(customerToMod.getName());
        custAddressField.setText(customerToMod.getAddress());
        custCountryCB.setItems(DBCountries.getAllCountries());
        custCountryCB.getSelectionModel().select(customerToMod.getCountry());
        custFLDCB.getSelectionModel().select(customerToMod.getFld());
        custPostCodeField.setText(customerToMod.getPostCode());
        custPhoneField.setText(customerToMod.getPhoneNumber());
        customerID.setText(String.valueOf(customerToMod.getId()));
    }

    /**
     * Validates the data entered into the add customer fields. If any field is found invalid, the user is alerted
     * via a custom notification from the Alerts class, populated with the provided string to explain the error.
     *
     * @return boolean b; false if information is not valid, true if it is.
     */
    private boolean validateInput() {
        boolean b = false;
        Countries country = custCountryCB.getValue();
        Divisions division = custFLDCB.getValue();
        if (custNameField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer name.\n");
        } else if (custAddressField.getText().isEmpty()) {
            Alerts.invalidData("\nYou must input a customer address.\n");
        } else if (country == null) {
           Alerts.invalidData("\nYou must choose a country.\n");
        } else if (division == null) {
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
     * combo box is currently set to.
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
     * Clears FLD selection when country combo box selection is changed to avoid mismatch between country and divisions.
     *
     * @param mouseEvent - not used
     */
    public void clearDivisions(MouseEvent mouseEvent) {
        custFLDCB.setValue(null);
    }


    /**
     * Method to handle saving the modified customer.
     *
     * @param actionEvent Used to identify the window from which the save was triggered to close it on a successful execution.
     */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateInput()) {
            customerToMod = new Customers(customerToMod.getId(), custNameField.getText(), custAddressField.getText(), custFLDCB.getValue(), custPostCodeField.getText(), custCountryCB.getValue(), custPhoneField.getText());
            DBCustomers.modifyCustomer(customerToMod);
            MainScreen.customers.set(indexToMod, customerToMod);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Confirms that the user wants to cancel modifying the appointment by providing an alert from the Alerts class.
     *
     * @param actionEvent Passed to a method in the Alerts class; used to close the window if the user confirms they want
     * to close without saving.
     */
    public void onCancelButton(ActionEvent actionEvent) {
        Alerts.cancelWithoutSaving(actionEvent);
    }
}
