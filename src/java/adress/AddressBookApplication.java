package adress;

/**
 * The AddressBookApplication class is the entry point for the address book application.
 * It initializes and displays the menu for user interaction.
 */
public class AddressBookApplication {

    public static void main(String[] args) {
        Menu menu = new Menu();

        menu.displayAndHandleMenu();
    }

}