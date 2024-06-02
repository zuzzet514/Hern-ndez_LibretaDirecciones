package adress.data;

/* acá están cada list.Permite varias operaciones como buscar/encontrar, agregar y eliminar AddressEntries. Es el libro
que contiene páginas (AdressEntry)
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AddressBook {
    private ArrayList<AddressEntry> adressBook = new ArrayList<>();

    private InputReader reader = new InputReader();

    public void addAdressEntry(AddressEntry newEntry) {
        if (isNotDuplicated(newEntry)) {
            adressBook.add(newEntry);
            sortAddressBook();
        }
    }

    public void searchAddressEntry() {
        String query = reader.readData("Enter last name or first letters:", this::isDataNotEmpty);
        ArrayList<AddressEntry> results = this.searchByLastName(query);
        messageWhenSearchingAddressEntry(results);
        printAdressEntryList(results);
    }


    public void deleteAddressEntry() {
        String queryLastName = reader.readData("Enter last name's contact you want to delete:", this::isDataNotEmpty);

        ArrayList<AddressEntry> results = searchByLastName(queryLastName);
        messageWhenSearchingAddressEntry(results);
        printAdressEntryList(results);

        if (results.size() > 0) {
            String stringSelectedEntry = reader.readData("Enter the number you want to delete:", this::isNumericDataValid);

            int selectedEntry = Integer.parseInt(stringSelectedEntry);

            int entryIndex = lookingForExistingEntryByIndex(results.get(selectedEntry));

            String wantToDelete = reader.readData("Enter 'y' to delete or 'n' to go back to menu", this::isYOrN);

            if (wantToDelete.equals('y')) {
                adressBook.remove(entryIndex);
            }
        }



    }

    public void showAddressBook() {
        printAdressEntryList(adressBook);
    }

    public ArrayList<AddressEntry> getAdressBook() {
        return adressBook;
    }

    public AddressEntry generateAddressEntryFromUserInput() {

        AddressEntry newAddressEntry = new AddressEntry();

        String name;
        String lastName;
        String street;
        String city;
        String state;
        String zip;
        String email;
        String phoneNumber;

        name = reader.readData("Name:", this::isDataNotEmpty);
        lastName = reader.readData("Last name:", this::isDataNotEmpty);
        street = reader.readData("Street:",this::isDataNotEmpty);
        city = reader.readData("City:", this::isDataNotEmpty);
        state = reader.readData("State:", this::isDataNotEmpty);
        zip = reader.readData("ZIP:", this::isNumericDataValid);
        email = reader.readData("Email:", this::isDataNotEmpty);
        phoneNumber = reader.readData("Phone number:", this::isNumericDataValid);

        newAddressEntry.setName(name);
        newAddressEntry.setLastName(lastName);
        newAddressEntry.setStreet(street);
        newAddressEntry.setCity(city);
        newAddressEntry.setState(state);
        newAddressEntry.setZip(zip);
        newAddressEntry.setEmail(email);
        newAddressEntry.setPhoneNumber(phoneNumber);

        return newAddressEntry;
    }

    private boolean isNumericDataValid(String data) {
        return data != null && data.matches("\\d+");
    }

    private boolean isDataNotEmpty(String data) {
        return data != null && !data.trim().isEmpty();
    }

    private boolean isYOrN(String data) {
        if (data == null || data.length() != 1) {
            return false;
        }
        char lowerCaseData = Character.toLowerCase(data.charAt(0));
        return lowerCaseData == 'y' || lowerCaseData == 'n';
    }

    private boolean isNotDuplicated(AddressEntry entry) {
        return lookingForExistingEntryByIndex(entry) == -1;
    }

    private int lookingForExistingEntryByIndex(AddressEntry entry) {
        int index = 0;

        boolean comparingNames;
        boolean comparingLastNames;
        boolean comparingStreets;
        boolean comparingCities;
        boolean comparingStates;
        boolean comparingZips;
        boolean comparingEmails;
        boolean comparingPhoneNumbers;

        for (AddressEntry existingEntry : adressBook) {
            comparingNames = existingEntry.getName().equalsIgnoreCase(entry.getName());
            comparingLastNames = existingEntry.getLastName().equalsIgnoreCase(entry.getLastName());
            comparingStreets = existingEntry.getStreet().equalsIgnoreCase(entry.getStreet());
            comparingCities = existingEntry.getCity().equalsIgnoreCase(entry.getCity());
            comparingStates = existingEntry.getState().equalsIgnoreCase(entry.getState());
            comparingZips = existingEntry.getZip().equalsIgnoreCase(entry.getZip());
            comparingEmails = existingEntry.getEmail().equalsIgnoreCase(entry.getEmail());
            comparingPhoneNumbers = existingEntry.getPhoneNumber().equalsIgnoreCase(entry.getPhoneNumber());


            if (comparingNames && comparingLastNames && comparingStreets && comparingCities && comparingStates && comparingZips && comparingEmails && comparingPhoneNumbers) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private void sortAddressBook() {
        Collections.sort(adressBook, new Comparator<AddressEntry>() {
            @Override
            public int compare(AddressEntry e1, AddressEntry e2) {
                return e1.getLastName().compareToIgnoreCase(e2.getLastName());
            }
        });
    }

    private ArrayList<AddressEntry> searchByLastName(String query) {
        ArrayList<AddressEntry> results = new ArrayList<>();

        String contactLastNameLoweredCase;
        String queryLoweredCase = query.toLowerCase();

        for (AddressEntry entry: adressBook) {
            contactLastNameLoweredCase = entry.getLastName().toLowerCase();

            if (contactLastNameLoweredCase.startsWith(queryLoweredCase)) {
                results.add(entry);
            }
        }

        return results;
    }

    private void messageWhenSearchingAddressEntry(ArrayList<AddressEntry> results) {
        if (results.size() == 0) {
            System.out.println("No matches found.");
        } else if (results.size() == 1) {
            System.out.println("The following contact was found: ");
        } else {
            System.out.println("The following " + results.size() + " contacts were found:");
        }
    }

    private void printAdressEntryList(ArrayList<AddressEntry> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i+1) + ": ");
            System.out.println(list.get(i));
        }
    }


}
