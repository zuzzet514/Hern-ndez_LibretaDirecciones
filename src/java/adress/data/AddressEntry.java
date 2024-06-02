package adress.data;

// pasar las direcciones a la clase Address
public class AddressEntry extends Address {
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;



    public AddressEntry() {
        super();
    }

    public AddressEntry(String name, String lastName, String street, String city, String state, String zip, String email, String phone_number) {
        super(street,city,state,zip);
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName + "\n" +this.getStreet() + "\n" + this.getCity() + ", " + this.getState() + ", zip: " + this.getZip() + "\n" +
                this.email + "\n" + this.phoneNumber + "\n";
    }
}
