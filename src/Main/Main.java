package Main;

import DBConnection.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Main.
 */
public class Main extends Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("login", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"), bundle);
        primaryStage.setTitle("Customer Scheduling System");
        primaryStage.setScene(new Scene(root, 300, 325));
        primaryStage.show();
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        String uname = "test";
//        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
//        Stage mainStage = new Stage();
//        mainStage.setTitle("Customer Scheduling System");
//        mainStage.setScene(new Scene(root, 1020, 745));
//        mainStage.show();
//        upcomingAppointments(DBUsers.getUserIDByName(uname));
//    }
}