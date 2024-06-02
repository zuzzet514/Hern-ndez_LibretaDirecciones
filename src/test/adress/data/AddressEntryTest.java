package adress.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressEntryTest {

    String name = "Zuzzet";
    String lastName = "Hernández";
    String street = "Oaxaca";
    String city = "Cosoleacaque";
    String state = "Veracruz";
    String zip = "96344";
    String email = "z@gmail.com";
    String phone_number = "9221951336";

    private AddressEntry entry;


    @BeforeEach
    void setUp() {
       entry = new AddressEntry(name,lastName, street, city, state,zip, email, phone_number);
    }


    @Test
    void getNameTest() {
        assertEquals(this.name, this.entry.getName());
    }

    @Test
    void setName() {
        String otherName = "Magdalena";

        assertNotEquals(this.entry.getName(), otherName);
        this.entry.setName(otherName);
        assertEquals(this.entry.getName(), otherName);
    }

    @Test
    void getLastName() {
        assertEquals(this.lastName, this.entry.getLastName());
    }

    @Test
    void setLastName() {
        String otherLastName = "Gutierrez";

        assertNotEquals(this.entry.getName(), otherLastName);
        this.entry.setName(otherLastName);
        assertEquals(this.entry.getName(), otherLastName);
    }

    @Test
    void setEmail() {
    }

    @Test
    void getPhoneNumber() {
    }

    @Test
    void setPhoneNumber() {
    }

}