package adress.data;
/**
 * The Address class represents a contact's address with a street, city, state, and zip code.
 */

public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

    /**
     * Default constructor for creating an empty Address object.
     */
    public Address() {
    }

    /***
     *  Constructor for creating an Address object with specified street, city, state, and zip code.
     *
     * @param street
     * @param city
     * @param state
     * @param zip
     */
    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Gets the street of the address.
     *
     * @return the street of the address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
     *
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the city of the address.
     *
     * @return the city of the address
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state of the address.
     *
     * @return the state of the address
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the address.
     *
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the zip code of the address.
     *
     * @return the zip code of the address
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the zip code of the address.
     *
     * @param zip the zip code to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Returns a string representation of the address.
     *
     * @return a string representation of the address
     */
    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }
}