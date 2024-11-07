package Model;

public class Countries {
    /**
     * The name of this country.
     */
    private final String countryName;
    /**
     * The country_id for this contact.
     */
    private final int countryID;

    /**
     * Instantiates a new Countries object.
     *
     * @param countryName country name - String
     * @param countryID   country ID - int
     */
    public Countries(String countryName, int countryID) {
        this.countryName = countryName;
        this.countryID = countryID;
    }

    /**
     * Gets country ID.
     * @return int countryID containing country ID.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Override default toString() method so that the combo box on the customer screens will display the name of the
     * country rather than the list of Countries objects.
     * @return String countryName containing country name.
     */
    @Override
    public String toString() {
        return countryName;
    }
}
