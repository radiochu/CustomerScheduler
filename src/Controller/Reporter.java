package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import Model.Appointments;
import Model.Contacts;
import Model.Types;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controller that handles the logic for the main screen based on mainScreen.fxml.
 */
public class Reporter implements Initializable {
    public Tab typesByMonth;
    public Button closeButton;
    public Tab scheduleByContact;
    public TableColumn<String, Types> monthCol;
    public TableColumn<String, Types> typeCol;
    public TableColumn<Integer, Types> totalCol;
    public TableView<Types> apptByMonthTV;
    public ComboBox<Contacts> contactCB;
    public TableView<Appointments> scheduleTV;
    public TableColumn<Integer, Appointments> apptIDCol;
    public TableColumn<String, Appointments> apptTitleCol;
    public TableColumn<String, Appointments> apptTypeCol;
    public TableColumn<String, Appointments> apptDescCol;
    public TableColumn<LocalDateTime, Appointments> apptStartCol;
    public TableColumn<LocalDateTime, Appointments> apptEndCol;
    public TableColumn<Integer, Appointments> apptCustIDCol;
    public TextArea apptByMonthTA;
    public Tab apptsByMonth;


    ObservableList<Types> typesByMonthList = DBAppointments.getApptTypesByMonth();
    ObservableList<Contacts> allContacts = DBContacts.getAllContacts();

    /**
     * Initializes the report screen. Sets up the combo box for listing contacts on the contact schedule tab.
     * Sets up factories for cell and property values to pull the correct properties from each object and
     * display them in the tableview.
     *
     * @param url - not used
     * @param resourceBundle - not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(allContacts);

        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        apptByMonthTV.setItems(typesByMonthList);

        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        apptCustIDCol.setCellValueFactory(new PropertyValueFactory<>("custID"));

        showApptsByMonth();

    }

    public void showTypesByMonth(Event event) {

    }

    public void showScheduleByContact() {
        scheduleTV.setItems(DBAppointments.getApptsByContact(contactCB.getValue().getId()));
    }

    /**
     * Pulls results from the database for appointments grouped by month and appends the returned strings to the
     * tableview for displaying the report.
     */
    public void showApptsByMonth() {
        apptByMonthTA.setText("Total Appointments By Month And Year\n");
        for (String i : DBAppointments.getApptsByMonth()) {
            apptByMonthTA.appendText(i);
        }
    }

    /**
     * Closes the reporting window.
     *
     * @param actionEvent Used to identify the window for which the close was triggered.
     */
    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


}
