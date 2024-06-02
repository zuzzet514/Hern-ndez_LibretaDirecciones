package adress.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readData() {
    }

    /*
    private final InputStream originalSystemIn = System.in;
    String name = "Zuzzet";
    String phoneNumber = "9221951336";



    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    void readDataTestWithValidInput() {
        String simulatedInput = "Zuzzet\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();
        String result = addressBook.readData("Name", addressBook::isDataNotEmpty);

        assertEquals(name, result);
    }

    @Test
    void testReadDataWithInvalidThenValidInput() {
        // Simulate invalid input followed by valid input
        String simulatedInput = "\n\nZuzzet\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();
        String result = addressBook.readData("Name", addressBook::isDataNotEmpty);

        assertEquals(name, result);
    }

    @Test
    void readDataTestWithValidNumericInput() {
        String simulatedInput = "9221951336\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();
        String result = addressBook.readData("Phone number", addressBook::isNumericDataValid);

        assertEquals(phoneNumber, result);
    }

    @Test
    void readDataTestWithInvalidNumericInputThenValidNumericInput() {
        String simulatedInput = "\n\n9221951336\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook addressBook = new AddressBook();
        String result = addressBook.readData("Phone number", addressBook::isNumericDataValid);

        assertEquals(phoneNumber, result);
    }

     */
}