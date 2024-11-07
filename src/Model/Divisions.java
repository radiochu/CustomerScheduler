package Model;

public class Divisions {
    /**
     * The name of this first-level division.
     */
    private final String divName;
    /**
     * The ID of the country this division corresponds to.
     */
    private final int countryID;

    /**
     * Instantiates a new Divisions object.
     *
     * @param divName     division name - String
     * @param countryID   country ID - int
     */
    public Divisions(String divName, int countryID) {
        this.divName = divName;
        this.countryID = countryID;
    }

    /**
     * Gets division name.
     * @return String divName containing division name.
     */
    public String getDivName() {
        return divName;
    }

    /**
     * Gets ID of country associated with division.
     * @return int countryID containing the country ID this division belongs to.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Override default toString() method so that the combo box on the customer screens will display the name of the
     * division rather than the list of Divisions objects.
     * @return String divName containing division name.
     */
    @Override
    public String toString(){
        return divName;
    }


}
