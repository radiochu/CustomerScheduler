package Model;

import java.time.LocalDateTime;


public class Appointments {
    /**
     * The title for the appointment.
     */
    private final String apptTitle;
    /**
     * The description of the appointment.
     */
    private final String apptDescription;
    /**
     * The location of the appointment.
     */
    private final String apptLocation;
    /**
     * The contact associated with this appointment.
     */
    private final Contacts apptContact;
    /**
     * The type of the appointment.
     */
    private final String apptType;
    /**
     * Start date and time for the appointment.
     */
    private final LocalDateTime apptStart;
    /**
     * End date and time for the appointment.
     */
    private final LocalDateTime apptEnd;
    /**
     * The ID of the customer associated with this appointment.
     */
    private final int custID;
    /**
     * The ID of the user associated with this appointment.
     */
    private final int userID;
    /**
     * The ID associated with this appointment.
     */
    private int apptID;

    /**
     * Instantiates a new Appointments object.
     *
     * @param apptID          appointment id - int
     * @param apptTitle       appointment title - String
     * @param apptDescription appointment description - String
     * @param apptLocation    appointment location - String
     * @param apptContact     appointment contact - Contacts object
     * @param apptStart       appointment start - LocalDateTime
     * @param apptEnd         appointment end - LocalDateTime
     * @param apptType        appointment type - String
     * @param custID          appointment customer ID - int
     * @param userID          appointment user ID - int
     */
    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, Contacts apptContact, LocalDateTime apptStart, LocalDateTime apptEnd, String apptType, int custID, int userID) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact = apptContact;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.custID = custID;
        this.userID = userID;
    }

    /**
     * Gets appointment ID.
     *
     * @return int apptID containing the appointment ID.
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * Sets appointment ID.
     *
     * @param apptID int; the ID to set to the Appointments object.
     */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /**
     * Gets appointment title.
     *
     * @return String apptTitle containing the appointment title.
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     * Gets appointment description.
     *
     * @return String apptDescription containing the appointment description.
     */
    public String getApptDescription() {
        return apptDescription;
    }

    /**
     * Gets appointment location.
     *
     * @return String apptLocation containing the appointment location.
     */
    public String getApptLocation() {
        return apptLocation;
    }

    /**
     * Gets appointment type.
     *
     * @return String apptType containing the appointment type.
     */
    public String getApptType() {
        return apptType;
    }

    /**
     * Gets appointment start time.
     *
     * @return LocalDateTime apptStart containing the appointment start time.
     */
    public LocalDateTime getApptStart() {
        return apptStart;
    }

    /**
     * Gets appointment end time.
     *
     * @return LocalDateTime apptEnd containing the appointment end time.
     */
    public LocalDateTime getApptEnd() {
        return apptEnd;
    }

    /**
     * Gets ID of this appointment's associated customer.
     *
     * @return int custID containing the appointment's customer ID.
     */
    public int getCustID() {
        return custID;
    }

    /**
     * Gets ID of this appointment's associated user.
     *
     * @return int userID containing the appointment's user ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets the contact associated with this appointment.
     *
     * @return Contacts apptContact containing the appointment's associated contact.
     */
    public Contacts getApptContact() {
        return apptContact;
    }

}
