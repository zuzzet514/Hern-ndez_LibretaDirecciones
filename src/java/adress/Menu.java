package adress;

import adress.data.AddressBook;
import adress.data.AddressEntry;
import adress.data.InputReader;

import java.util.NoSuchElementException;

/*
esta clase se utiliza para mostrar las opciones de menú al usuario
displayMenu() muestra:
a) Carga de entradas desde un archivo.
b) Agregar
c) Eliminar
d) Buscar:
e) Mostrar
f) Salir


*/

public class Menu {
    private final AddressBook ADDRESS_BOOK;
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

    public Menu(AddressBook addressBook) {
        this.ADDRESS_BOOK = addressBook;
    }

    public void displayAndHandleMenu() {
        displayMenu();
        getUserSelection();
    }

    private void displayMenu(){
        String delimiter = "======================================";
        System.out.println(delimiter);
        System.out.println("Choose an option from the menu");
        for (int i = 0; i < BULLET_LETTERS.length && i < OPTIONS.length; i++) {
            System.out.print(BULLET_LETTERS[i] + ") " + OPTIONS[i] + "\n");
        }
        System.out.println(delimiter);
    }

    private void getUserSelection() {
        try {
            String rawOption = READER.readData(" ", this::isInputInBullets);
            char option = rawOption.charAt(0);
            executeSelectedOption(option);
        } catch (NoSuchElementException ignored) {}

    }

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

    private boolean isInputInBullets(String input) {
        char option = input.charAt(0);
        for (char bullet: BULLET_LETTERS) {
            if (bullet == option) {
                return true;
            }
        }
        return false;
    }

    private void exit() {
        System.out.println("Bye! ");
        System.exit(0);
    }


}
