package adress;

import adress.data.AddressBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

class MenuTest {

    private final InputStream originalSystemIn = System.in;
    //private  Menu menu =  new Menu();

    @BeforeEach
    void setUp() {
        System.out.println("Setting up test...");
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.out.println("Tear down test...");
        clearSingletonInstance();
    }

    private void clearSingletonInstance() {
        try {
            Field instance = AddressBook.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void TestSelectInvalidOption() {
        String simulatedInputForOption = "z" + "\n";
        ByteArrayInputStream inputStreamForOption = new ByteArrayInputStream(simulatedInputForOption.getBytes());
        System.setIn(inputStreamForOption);

        AddressBook ad = AddressBook.getInstance();
        Menu menu = new Menu(ad);
        menu.displayAndHandleMenu();


    }

    @Test
    void testUploadFromFileCall() {
        String simulatedInputForOption = "a" + "\n";
        ByteArrayInputStream inputStreamForOption = new ByteArrayInputStream(simulatedInputForOption.getBytes());
        System.setIn(inputStreamForOption);

        AddressBook ad = AddressBook.getInstance();
        Menu menu = new Menu(ad);
        menu.displayAndHandleMenu();

    }

    @Test
    void testAddEntryCall() {
        String simulatedInputForOption = "b" + "\n";
        ByteArrayInputStream inputStreamForOption = new ByteArrayInputStream(simulatedInputForOption.getBytes());
        System.setIn(inputStreamForOption);

        AddressBook ad = AddressBook.getInstance();
        Menu menu = new Menu(ad);
        menu.displayAndHandleMenu();
    }

    @Test
    void testDeleteCall() {
        String simulatedInputForOption = "c" + "\n";
        ByteArrayInputStream inputStreamForOption = new ByteArrayInputStream(simulatedInputForOption.getBytes());
        System.setIn(inputStreamForOption);

        AddressBook ad = AddressBook.getInstance();
        Menu menu = new Menu(ad);
        menu.displayAndHandleMenu();
    }

    @Test
    void testSearchCall() {
        String simulatedInputForOption = "d" + "\n";
        ByteArrayInputStream inputStreamForOption = new ByteArrayInputStream(simulatedInputForOption.getBytes());
        System.setIn(inputStreamForOption);

        AddressBook ad = AddressBook.getInstance();
        Menu menu = new Menu(ad);
        menu.displayAndHandleMenu();
    }

    @Test
    void testSelectAnOptionShowWithNoEntries() {
        String simulatedInput = "e" + "\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        AddressBook ad = AddressBook.getInstance();
        Menu menu = new Menu(ad);
        menu.displayAndHandleMenu();

    }

    @Test
    void testExit() {
        String simulatedInputForOption = "f" + "\n";
        ByteArrayInputStream inputStreamForOption = new ByteArrayInputStream(simulatedInputForOption.getBytes());
        System.setIn(inputStreamForOption);

        AddressBook ad = AddressBook.getInstance();
        Menu menu = new Menu(ad);
        menu.displayAndHandleMenu();
    }
}