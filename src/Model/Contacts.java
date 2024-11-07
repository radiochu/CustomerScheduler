package Model;

public class Contacts {
    /**
     * The name of this contact.
     */
    private final String name;
    /**
     * The contact_id for this contact.
     */
    private final int id;

    /**
     * Instantiates a new Contacts object.
     *
     * @param name contact name - String
     * @param id   contact ID - int
     */
    public Contacts(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Getter for contact name.
     * @return String name containing contact name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for contact ID.
     * @return int id containing contact ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Override default toString() method so that the combo box on the appointment screens will display the name of the
     * contact rather than the list of Contacts objects.
     * @return String name containing contact name.
     */
    @Override
    public String toString() {
        return name;
    }
}
