package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class that creates a connection to the MySQL database via the JDBC driver, and provides methods for opening, closing,
 * and getting the connection for use in the application.
 */
public abstract class JDBC {
    /**
     * The protocol used to connect to the database.
     */
    private static final String protocol = "jdbc";
    /**
     * The type of database the application will be connecting to.
     */
    private static final String vendor = ":mysql:";
    /**
     * The network location where the database can be found.
     */
    private static final String location = "//localhost/";
    /**
     * The name of the database to connect to.
     */
    private static final String databaseName = "client_schedule";
    /**
     * A string built from all of the above attributes that creates the jdbcUrl that will be passed to the driver and used
     * to create the connection object.
     */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    /**
     * The driver to be used for the JDBC connection.
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * The username that will be used to connect to the database.
     */
    private static final String userName = "sqlUser";
    /**
     * The password that will be used to connect to the database.
     */
    private static final String password = "Passw0rd!";

    public static Connection connection;

    /**
     * Open connection. Attempts to pass the JDBC URL, username, and password to the driver to create a connection. Runs
     * once when the application is initialized, then leaves the connection open so that it can be referenced throughout
     * the application.
     */
    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method allowing other classes to access the connection when needed.
     *
     * @return the connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Close connection. Runs once when the application is closed to terminate the connection.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}