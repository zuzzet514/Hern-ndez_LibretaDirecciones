package adress;

import adress.data.AddressBook;
import adress.data.AddressEntry;
import adress.data.InputReader;

import java.util.NoSuchElementException;

/**
 * This class is used to display menu options to the user.
 * The displayMenu() method shows the following options:
 * a) Load entries from a file.
 * b) Add a new entry.
 * c) Delete an entry.
 * d) Search for entries.
 * e) Show all entries.
 * f) Exit the application.
 */
public class Menu {
    private final AddressBook ADDRESS_BOOK = AddressBook.getInstance();
    private final char[] BULLET_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f'};
    private final String[] OPTIONS = {
            "Upload from a file",
            "Add",
            "Delete",
            "Search",
            "Show",
            "Exit"
    };
    private final InputReader READER = new InputReader();

    /**
     * Constructs a Menu object.
     */
    public Menu() {

    }

    /**
     * Displays the menu and handles user selection in an infinite loo until the user decides to exit
     */
    public void displayAndHandleMenu() {
        while (true) {
            displayMenu();
            getUserSelection();
        }
    }

    /**
     * Displays the menu options to the user.
     */
    private void displayMenu(){
        String delimiter = "======================================";
        System.out.println(delimiter);
        System.out.println("Choose an option from the menu");
        for (int i = 0; i < BULLET_LETTERS.length && i < OPTIONS.length; i++) {
            System.out.print(BULLET_LETTERS[i] + ") " + OPTIONS[i] + "\n");
        }
        System.out.println(delimiter);
    }

    /**
     * Gets the user's menu selection and executes the corresponding action.
     */
    private void getUserSelection() {
        try {
            String rawOption = READER.readData(" ", this::isInputInBullets);
            char option = rawOption.charAt(0);
            executeSelectedOption(option);
        } catch (NoSuchElementException ignored) {}

    }

    /**
     * Executes the action corresponding to the user's menu selection.
     *
     * @param selectedOption the menu option selected by the user
     */
    private void executeSelectedOption(char selectedOption) {

        switch (selectedOption) {
            case 'a':
                ADDRESS_BOOK.addAdressEntriesFromFile(); break;
            case 'b':
                AddressEntry newEntry =  ADDRESS_BOOK.generateAddressEntryFromUserInput();
                ADDRESS_BOOK.addAddressEntry(newEntry); break;
            case 'c':
                ADDRESS_BOOK.deleteAddressEntry(); break;
            case 'd':
                ADDRESS_BOOK.searchAddressEntry(); break;
            case 'e':
                ADDRESS_BOOK.showAddressBook(); break;
            case 'f':
                this.exit();
                break;
            default:
                System.out.println("Something went wrong. Please try again"); break;
        }
    }

    /**
     * Checks if the user input corresponds to one of the menu bullet letters.
     *
     * @param input the user input to check
     * @return true if the input is a valid bullet letter, false otherwise
     */
    private boolean isInputInBullets(String input) {
        char option = input.charAt(0);
        for (char bullet: BULLET_LETTERS) {
            if (bullet == option) {
                return true;
            }
        }
        return false;
    }

    /**
     * Exits the application with a goodbye message.
     */
    private void exit() {
        System.out.println("Bye! See you later");
        System.exit(0);
    }
}