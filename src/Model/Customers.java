package Model;

public class Customers {
    /**
     * The customer name.
     */
    private String name;
    /**
     * The customer address.
     */
    private String address;
    /**
     * The customer ID.
     */
    private int id;
    /**
     * The first-level division for the customer address.
     */
    private Divisions fld;
    /**
     * The postal code for the customer address.
     */
    private String postCode;
    /**
     * The country for the customer address.
     */
    private Countries country;
    /**
     * The customer phone number.
     */
    private String phoneNumber;

    /**
     * Instantiates a new Customers object.
     *
     * @param id          customer ID - int
     * @param name        customer name - String
     * @param address     customer address - String
     * @param fld         customer first-level division - Divisions object
     * @param postCode    customer postal code - String
     * @param country     customer country - Countries object
     * @param phoneNumber customer phone number - String
     */
    public Customers(int id, String name, String address, Divisions fld, String postCode, Countries country, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fld = fld;
        this.postCode = postCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets customer phone number.
     *
     * @return String phoneNumber containing the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets customer address post code.
     *
     * @return String postCode containing the post code for the customer's address.
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Gets customer address country.
     *
     * @return Countries country, the Countries object representing the country for the customer's address.
     */
    public Countries getCountry() {
        return country;
    }


    /**
     * Gets customer address first-level division.
     *
     * @return Divisions fld, the Divisions object representing the first-level division for the customer's address.
     */
    public Divisions getFld() {
        return fld;
    }


    /**
     * Gets customer ID.
     *
     * @return int id, the customer's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets customer ID.
     *
     * @param id int; The ID to be assigned to this customer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets customer name.
     *
     * @return String name, the customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets customer name.
     *
     * @param name String; name to be assigned to this customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets customer address.
     *
     * @return String address, the customer's address.
     */
    public String getAddress() {
        return address;
    }
}
