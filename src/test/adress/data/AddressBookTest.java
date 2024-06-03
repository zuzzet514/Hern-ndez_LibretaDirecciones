package adress.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    private final InputStream originalSystemIn = System.in;
    String name = "Zuzzet";
    String lastName = "Hernández";
    String street = "Oaxaca";
    String city = "Cosoleacaque";
    String state = "Veracruz";
    String zip = "96344";
    String email = "z@gmail.com";
    String phoneNumber = "9221951336";

    String nameVersionTwo = "Elisa";
    String lastNameVersionTwo = "Suárez";
    String streetVersionTwo = "Oaxaca";
    String cityVersionTwo = "Cosoleacaque";
    String stateVersionTwo = "Veracruz";
    String zipVersionTwo = "96344";
    String emailVersionTwo = "elisa@gmail.com";
    String phoneNumberVersionTwo = "9221951336";

    @BeforeEach
    void setUp() {
        System.out.println("Setting up test...");
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.out.println("Tear down test...");
    }

    @Test
    void testAddressEntryWithValidDataFromUserInput() {
        String simulatedInput = name + "\n" + lastName + "\n" + street + "\n" + city + "\n" + state + "\n" + zip + "\n" + email + "\n" + phoneNumber + "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook localAddressBook = new AddressBook();

        AddressEntry localEntry = localAddressBook.generateAddressEntryFromUserInput();

        assertEquals(name, localEntry.getName());
        assertEquals(lastName, localEntry.getLastName());
        assertEquals(street, localEntry.getStreet());
        assertEquals(city, localEntry.getCity());
        assertEquals(state, localEntry.getState());
        assertEquals(zip, localEntry.getZip());
        assertEquals(email, localEntry.getEmail());
        assertEquals(phoneNumber, localEntry.getPhoneNumber());

    }

    @Test
    void testAddressEntryWithInvalidDataThenValidDataFromUserInput() {
        String simulatedInput = "\n" + name + "\n" + "\n" + lastName + "\n" +  "\n" + street + "\n" + city + "\n" + state +
                "\n" + "hola\n" + zip + "\n" + email + "\n" +"hola\n" + phoneNumber + "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();

        AddressEntry entry = addressBook.generateAddressEntryFromUserInput();

        assertEquals(name, entry.getName());
        assertEquals(lastName, entry.getLastName());
        assertEquals(street, entry.getStreet());
        assertEquals(city, entry.getCity());
        assertEquals(state, entry.getState());
        assertEquals(zip, entry.getZip());
        assertEquals(email, entry.getEmail());
        assertEquals(phoneNumber, entry.getPhoneNumber());
    }

    @Test
    void testAddAddressEntryAndSorting() {

        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName(lastName);
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(nameVersionTwo);
        secondEntry.setLastName(lastNameVersionTwo);
        secondEntry.setStreet(streetVersionTwo);
        secondEntry.setCity(cityVersionTwo);
        secondEntry.setState(stateVersionTwo);
        secondEntry.setZip(zipVersionTwo);
        secondEntry.setEmail(emailVersionTwo);
        secondEntry.setPhoneNumber(phoneNumberVersionTwo);

        addressBook.addAdressEntry(secondEntry);

        ArrayList<AddressEntry> entries = addressBook.getAdressBook();

        assertEquals(2, entries.size());

        AddressEntry firstPlace = entries.get(0);
        AddressEntry secondPlace = entries.get(1);

        assertTrue(firstPlace.getLastName().compareTo(secondPlace.getLastName()) < 0);
        addressBook.showAddressBook();

    }

    @Test
    void testNotAllowingDuplicateEntries() {
        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName(lastName);
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(name);
        secondEntry.setLastName(lastName);
        secondEntry.setStreet(street);
        secondEntry.setCity(city);
        secondEntry.setState(state);
        secondEntry.setZip(zip);
        secondEntry.setEmail(email);
        secondEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(secondEntry);

        ArrayList<AddressEntry> entries = addressBook.getAdressBook();

        assertEquals(1, entries.size());


    }

    @Test
    void testSearchAndFindOneEntry() {
        String simulatedInput = "Her";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName(lastName);
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(nameVersionTwo);
        secondEntry.setLastName(lastNameVersionTwo);
        secondEntry.setStreet(streetVersionTwo);
        secondEntry.setCity(cityVersionTwo);
        secondEntry.setState(stateVersionTwo);
        secondEntry.setZip(zipVersionTwo);
        secondEntry.setEmail(emailVersionTwo);
        secondEntry.setPhoneNumber(phoneNumberVersionTwo);

        addressBook.addAdressEntry(secondEntry);

        addressBook.searchAddressEntry();
    }

    @Test
    void  testSearchAndNotFindEntry() {
        String simulatedInput = "Delgado";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName(lastName);
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(nameVersionTwo);
        secondEntry.setLastName(lastNameVersionTwo);
        secondEntry.setStreet(streetVersionTwo);
        secondEntry.setCity(cityVersionTwo);
        secondEntry.setState(stateVersionTwo);
        secondEntry.setZip(zipVersionTwo);
        secondEntry.setEmail(emailVersionTwo);
        secondEntry.setPhoneNumber(phoneNumberVersionTwo);

        addressBook.addAdressEntry(secondEntry);

        addressBook.searchAddressEntry();
    }

    @Test
    void testFindMoreThanOneEntry() {
        String simulatedInput = "Su";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName("Suárez");
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(nameVersionTwo);
        secondEntry.setLastName(lastNameVersionTwo);
        secondEntry.setStreet(streetVersionTwo);
        secondEntry.setCity(cityVersionTwo);
        secondEntry.setState(stateVersionTwo);
        secondEntry.setZip(zipVersionTwo);
        secondEntry.setEmail(emailVersionTwo);
        secondEntry.setPhoneNumber(phoneNumberVersionTwo);

        addressBook.addAdressEntry(secondEntry);

        addressBook.searchAddressEntry();
    }

    @Test
    void testDeleteEntry() {
        String simulatedInput = "Her" + "\n" + "1" + "\n" + "Y" + "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName(lastName);
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(nameVersionTwo);
        secondEntry.setLastName(lastNameVersionTwo);
        secondEntry.setStreet(streetVersionTwo);
        secondEntry.setCity(cityVersionTwo);
        secondEntry.setState(stateVersionTwo);
        secondEntry.setZip(zipVersionTwo);
        secondEntry.setEmail(emailVersionTwo);
        secondEntry.setPhoneNumber(phoneNumberVersionTwo);

        addressBook.addAdressEntry(secondEntry);

        addressBook.showAddressBook();

        addressBook.deleteAddressEntry();

        ArrayList<AddressEntry> entries = addressBook.getAdressBook();

        assertEquals(1, entries.size());

        addressBook.showAddressBook();

    }

    @Test
    void testDeleteNotexistingEntry() {
        String simulatedInput = "Her" + "\n" + "1" + "\n" + "y" + "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName("Suárez");
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(nameVersionTwo);
        secondEntry.setLastName(lastNameVersionTwo);
        secondEntry.setStreet(streetVersionTwo);
        secondEntry.setCity(cityVersionTwo);
        secondEntry.setState(stateVersionTwo);
        secondEntry.setZip(zipVersionTwo);
        secondEntry.setEmail(emailVersionTwo);
        secondEntry.setPhoneNumber(phoneNumberVersionTwo);

        addressBook.addAdressEntry(secondEntry);

        addressBook.showAddressBook();

        addressBook.deleteAddressEntry();

        ArrayList<AddressEntry> entries = addressBook.getAdressBook();

        assertEquals(2, entries.size());

        addressBook.showAddressBook();
    }

    @Test
    void testIncorrectInputThenCorrectInputForDeletingEntry() {
        String simulatedInput = "Her" + "\n" + "4\n" +"1" + "\n" + "o\n" + "y" + "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();

        AddressEntry firstEntry = new AddressEntry();
        firstEntry.setName(name);
        firstEntry.setLastName(lastName);
        firstEntry.setStreet(street);
        firstEntry.setCity(city);
        firstEntry.setState(state);
        firstEntry.setZip(zip);
        firstEntry.setEmail(email);
        firstEntry.setPhoneNumber(phoneNumber);

        addressBook.addAdressEntry(firstEntry);

        AddressEntry secondEntry = new AddressEntry();
        secondEntry.setName(nameVersionTwo);
        secondEntry.setLastName(lastNameVersionTwo);
        secondEntry.setStreet(streetVersionTwo);
        secondEntry.setCity(cityVersionTwo);
        secondEntry.setState(stateVersionTwo);
        secondEntry.setZip(zipVersionTwo);
        secondEntry.setEmail(emailVersionTwo);
        secondEntry.setPhoneNumber(phoneNumberVersionTwo);

        addressBook.addAdressEntry(secondEntry);

        addressBook.showAddressBook();

        addressBook.deleteAddressEntry();

        ArrayList<AddressEntry> entries = addressBook.getAdressBook();

        assertEquals(1, entries.size());

        addressBook.showAddressBook();
    }

}