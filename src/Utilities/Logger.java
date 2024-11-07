package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public abstract class Logger {
    /**
     * Creates a file to which the log will be written.
     */
    private static final File logFile = new File("src/login_activity.txt");

    /**
     * Creates a FileWriter and PrintWriter object. When passed a string, it will append the string to the named log file.
     *
     * @param log String to add to the log file.
     * @throws IOException Thrown if file to be written to does not exist.
     */
    public static void logActivity(String log) throws IOException {

        FileWriter fw = new FileWriter(logFile, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(log);
        pw.close();

    }

}
