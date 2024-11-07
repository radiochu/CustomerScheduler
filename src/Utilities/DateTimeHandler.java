package Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.time.format.DateTimeFormatter;

public abstract class DateTimeHandler {
    /**
     * Formatter to pull just the hours out of a LocalDateTime.
     */
    static final DateTimeFormatter getHours = DateTimeFormatter.ofPattern("HH");
    /**
     * Formatter to pull just the minutes out of a LocalDateTime.
     */
    static final DateTimeFormatter getMinutes = DateTimeFormatter.ofPattern("mm");
    /**
     * Formatter to pull just the seconds out of a LocalDateTime.
     */
    static final DateTimeFormatter getSeconds = DateTimeFormatter.ofPattern("ss");

    /**
     * LocalDateTime representing the start time.
     *
     * @param apptDate  the date selected for the appointment in the Add/Modify Appointment datepickers.
     * @param apptStart the start time selected for the appointment in the Add/Modify Appointment time combo boxes.
     * @return formatted start time as LocalDateTime object.
     */
    public static LocalDateTime startTime(LocalDate apptDate, LocalTime apptStart) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptStart)), Integer.parseInt(getMinutes.format(apptStart)), Integer.parseInt(getSeconds.format(apptStart)));
    }

    /**
     * LocalDateTime representing the end time.
     *
     * @param apptDate  the date selected for the appointment in the Add/Modify Appointment datepickers.
     * @param apptEnd the end time selected for the appointment in the Add/Modify Appointment time combo boxes.
     * @return formatted end time as LocalDateTime object.
     */
    public static LocalDateTime endTime(LocalDate apptDate, LocalTime apptEnd) {
        return LocalDateTime.of(apptDate.getYear(), apptDate.getMonthValue(), apptDate.getDayOfMonth(), Integer.parseInt(getHours.format(apptEnd)), Integer.parseInt(getMinutes.format(apptEnd)), Integer.parseInt(getSeconds.format(apptEnd)));
    }

    /**
     * Creates a list of times to be populated in the start and end combo boxes on the Add/Modify Appointment screens.
     * Provides conversion so that the list of times shows only the business's hours of operation, converted to the
     * local time zone of the user.
     *
     * @param date the date selected for the appointment in the Add/Modify Appointment datepickers.
     * @return ObservableList timeList containing the LocalTime objects to populate the time list combo boxes.
     */
    public static ObservableList<LocalTime> setTimeList(LocalDate date) {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        LocalDateTime start = LocalDateTime.of(date, LocalTime.of(8, 0));
        LocalDateTime end = LocalDateTime.of(date, LocalTime.of(22, 0));
        while (start.isBefore(end.plusSeconds(1))) {
            ZonedDateTime startEST = start.atZone(ZoneId.of("America/New_York"));
            ZonedDateTime startLocal = startEST.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime startLDT = startLocal.toLocalDateTime();
            LocalTime startTime = startLDT.toLocalTime();
            timeList.add(startTime);
            start = start.plusMinutes(10);
        }

        return timeList;
    }
}
