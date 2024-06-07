package adress.data;

/**
 * The AddressEntry class represents an entry in an address book.
 * It includes personal information such as name, last name, email, and phone number,
 * along with address information inherited from the Address class.
 */
public class AddressEntry extends Address {
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;



    /**
     * Default constructor for creating an empty AddressEntry object.
     */
    public AddressEntry() {
        super();
    }

    /**
     * Constructor for creating an AddressEntry object with specified details.
     *
     * @param name the first name of the person
     * @param lastName the last name of the person
     * @param street the street name and number of the address
     * @param city the city of the address
     * @param state the state of the address
     * @param zip the zip code of the address
     * @param email the email address of the person
     * @param phone_number the phone number of the person
     */
    public AddressEntry(String name, String lastName, String street, String city, String state, String zip, String email, String phone_number) {
        super(street,city,state,zip);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phone_number;
    }

    /**
     * Gets the first name of the person.
     *
     * @return the first name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the person.
     *
     * @param name the first name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name of the person.
     *
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the person.
     *
     * @return the email address of the person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the person.
     *
     * @return the phone number of the person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the address entry.
     * It's formatted in thw way they are suppoused to be show in console
     *
     * @return a string representation of the address entry
     */
    @Override
    public String toString() {
        return this.name + " " + this.lastName + "\n" +this.getStreet() + "\n" + this.getCity() + ", " + this.getState() + ", zip: " + this.getZip() + "\n" +
                this.email + "\n" + this.phoneNumber + "\n";
    }
}