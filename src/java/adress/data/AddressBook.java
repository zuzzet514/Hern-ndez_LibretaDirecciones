package adress.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * The AddressBook class manages a list of AddressEntry objects.
 * It supports operations such as adding entries, deleting entries,
 * searching for entries, and loading entries from a file.
 */
public class AddressBook {

    private static AddressBook instance;
    private final ArrayList<AddressEntry> ADDRESS_ENTRY_LIST = new ArrayList<>();

    private final InputReader READER = new InputReader();

    private AddressBook() {}

    /**
     * Returns the singleton instance of the AddressBook class.
     *
     * @return the singleton instance of AddressBook
     */
    public static AddressBook getInstance() {
        if (instance == null) {
            synchronized (AddressBook.class) {
                if (instance == null) {
                    instance = new AddressBook();
                }
            }
        }
        return instance;
    }

    /**
     * Adds a new AddressEntry to the address book if it is not duplicated.
     * The address book is sorted after adding the new entry.
     *
     * @param newEntry the AddressEntry to add
     */
    public void addAddressEntry(AddressEntry newEntry) {
        if (isNotDuplicated(newEntry)) {
            ADDRESS_ENTRY_LIST.add(newEntry);
            sortAddressBook();
        }
    }

    /**
     * Prompts the user to enter a file name and adds address entries from the specified file.
     * Only entries that pass validation checks are added to the address book.
     */
    public void addAdressEntriesFromFile() {

        try {
            String filePath = READER.readData("Enter the file's name:", this::isATxtFile);

            ArrayList<AddressEntry> entries = readEntriesFromFile(filePath);
            for (AddressEntry entry : entries) {
                addAddressEntry(entry);
            }
        } catch (NoSuchElementException exception) {
            System.out.println("No input provided. Please try again.");
        } catch (Exception exception) {
            System.out.println("An unexpected error occurred: " + exception.getMessage());
        }


    }

    /**
     * Searches for address entries by last name or the first letters of the last name.
     * Prompts the user for input and displays the search results.
     *
     * @return a list of AddressEntry objects that match the search query
     */
    public ArrayList<AddressEntry> searchAddressEntry() {
        String query = READER.readData("Enter last name or first letters:", this::isDataNotEmpty);
        ArrayList<AddressEntry> results = this.searchByLastName(query);
        messageWhenSearchingAddressEntry(results);
        printAdressEntryList(results);
        return results;
    }


    /**
     * Deletes an address entry from the address book.
     * Prompts the user to select an entry from search results and confirm the deletion.
     */
    public void deleteAddressEntry() {
        ArrayList<AddressEntry> results = searchAddressEntry();

        if (results.size() > 0) {
            String stringSelectedEntry;

            int selectedEntry;

            do {
                stringSelectedEntry = READER.readData("Enter the number you want to delete:", this::isNumericDataValid);
                selectedEntry = Integer.parseInt(stringSelectedEntry);
                selectedEntry-=1;

            } while (!isBetweenEntrySize(selectedEntry, results));

            int entryIndex = lookingForExistingEntryByIndex(results.get(selectedEntry));

            String wantToDelete = READER.readData("Enter 'y' to delete or 'n' to go back to menu", this::isYOrN).toLowerCase();

            if (wantToDelete.equals("y")) {
                ADDRESS_ENTRY_LIST.remove(entryIndex);
            }
        }

    }

    /**
     * Displays all address entries in the address book.
     */
    public void showAddressBook() {
        printAdressEntryList(ADDRESS_ENTRY_LIST);
    }

    /**
     * Gets the list of address entries in the address book.
     *
     * @return the list of AddressEntry objects
     */
    public ArrayList<AddressEntry> getADDRESS_ENTRY_LIST() {
        return ADDRESS_ENTRY_LIST;
    }

    /**
     * Prompts the user for input and generates a new AddressEntry object based on the provided data.
     *
     * @return a new AddressEntry object
     */
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

        name = READER.readData("Name:", this::isDataNotEmpty);
        lastName = READER.readData("Last name:", this::isDataNotEmpty);
        street = READER.readData("Street:",this::isDataNotEmpty);
        city = READER.readData("City:", this::isDataNotEmpty);
        state = READER.readData("State:", this::isDataNotEmpty);
        zip = READER.readData("ZIP:", this::isNumericDataValid);
        email = READER.readData("Email:", this::isDataNotEmpty);
        phoneNumber = READER.readData("Phone number:", this::isNumericDataValid);

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

    /**
     * Reads address entries from a file specified by the file path.
     * Only valid entries are added to the list.
     *
     * @param filePath the path to the file containing address entries
     * @return a list of AddressEntry objects read from the file
     */
    private ArrayList<AddressEntry> readEntriesFromFile(String filePath) {

        // Use the current directory if the file path is relative
        File file = new File(filePath);

        if (!file.isAbsolute()) {
            file = new File(System.getProperty("user.dir"), filePath);
        }

        ArrayList<AddressEntry> entries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String firstLineThatCorrespondsName;

            while ((firstLineThatCorrespondsName = br.readLine()) != null) {

                AddressEntry entry = new AddressEntry();
                entry.setName(firstLineThatCorrespondsName);


                String lastName = br.readLine();
                String street = br.readLine();
                String city = br.readLine();
                String state = br.readLine();
                String zip = br.readLine();
                String email = br.readLine();
                String phoneNumber = br.readLine();
                br.readLine();

                if (isDataNotEmpty(lastName)){
                    entry.setLastName(lastName);
                } else {
                    continue;
                }

                if (isDataNotEmpty(street)){
                    entry.setStreet(street);
                } else {
                    continue;
                }

                if (isDataNotEmpty(city)){
                    entry.setCity(city);
                } else {
                    continue;
                }

                if (isDataNotEmpty(state)){
                    entry.setState(state);
                } else {
                    continue;
                }

                if (isNumericDataValid(zip)){
                    entry.setZip(zip);
                } else {
                    continue;
                }

                if (isDataNotEmpty(email)){
                    entry.setEmail(email);
                } else {
                    continue;
                }

                if (isDataNotEmpty(phoneNumber)){
                    entry.setPhoneNumber(phoneNumber);
                } else {
                    continue;
                }

                entries.add(entry);

            }
        } catch (FileNotFoundException notFoundException) {
            System.out.println("Huh? I can't find that file, sorry. Try again.");
        } catch (IOException e) {
            System.out.println("There are some problems with the reading. Please try again.");
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Validates if the given data is numeric.
     *
     * @param data the data to validate
     * @return true if the data is numeric, false otherwise
     */
    private boolean isNumericDataValid(String data) {
        return data != null && data.matches("\\d+");
    }

    /**
     * Validates if the given data is not empty.
     *
     * @param data the data to validate
     * @return true if the data is not empty, false otherwise
     */
    private boolean isDataNotEmpty(String data) {
        return data != null && !data.trim().isEmpty();
    }

    /**
     * Validates if the given data is 'y' or 'n'.
     *
     * @param data the data to validate
     * @return true if the data is 'y' or 'n', false otherwise
     */
    private boolean isYOrN(String data) {
        if (data == null || data.length() != 1) {
            return false;
        }
        char lowerCaseData = Character.toLowerCase(data.charAt(0));
        return lowerCaseData == 'y' || lowerCaseData == 'n';
    }

    /**
     * Checks if the selection is within the bounds of the address entry list size.
     *
     * @param selection the selection index
     * @param list the list of AddressEntry objects
     * @return true if the selection is within bounds, false otherwise
     */
    private boolean isBetweenEntrySize(int selection, ArrayList<AddressEntry> list) {
        return selection < list.size() && selection > -1;
    }

    /**
     * Checks if the given AddressEntry is not duplicated in the address book.
     *
     * @param entry the AddressEntry to check
     * @return true if the entry is not duplicated, false otherwise
     */
    private boolean isNotDuplicated(AddressEntry entry) {
        return lookingForExistingEntryByIndex(entry) == -1;
    }

    /**
     * Validates if the file provided is a txt file
     *
     * @param file the name and direction's file
     * @return true if the file / path ends with '.txt', false otherwise
     */
    private boolean isATxtFile(String file){
        if (file == null || file.trim().isEmpty()) {
            System.out.println("Input is null or empty.");
            return false;
        }
        if (!file.endsWith(".txt")) {
            System.out.println("File does not end with .txt.");
            return false;
        }
        return true;
    }

    /**
     * Finds the index of an existing AddressEntry in the address book that matches the given entry.
     * Comparison is case-insensitive and checks all fields for equality.
     *
     * @param entry the AddressEntry to find
     * @return the index of the matching AddressEntry, or -1 if no match is found
     */
    private int lookingForExistingEntryByIndex(AddressEntry entry) {
        int index = 0;

        boolean araNamesEquals;
        boolean areLastNamesEquals;
        boolean areStreetEquals;
        boolean areCitiesEquals;
        boolean areStatesEquals;
        boolean areZipsEquals;
        boolean areEmailsEquals;
        boolean arePhoneNumbersEquals;

        for (AddressEntry existingEntry : ADDRESS_ENTRY_LIST) {
            araNamesEquals = existingEntry.getName().equalsIgnoreCase(entry.getName());
            areLastNamesEquals = existingEntry.getLastName().equalsIgnoreCase(entry.getLastName());
            areStreetEquals = existingEntry.getStreet().equalsIgnoreCase(entry.getStreet());
            areCitiesEquals = existingEntry.getCity().equalsIgnoreCase(entry.getCity());
            areStatesEquals = existingEntry.getState().equalsIgnoreCase(entry.getState());
            areZipsEquals = existingEntry.getZip().equalsIgnoreCase(entry.getZip());
            areEmailsEquals = existingEntry.getEmail().equalsIgnoreCase(entry.getEmail());
            arePhoneNumbersEquals = existingEntry.getPhoneNumber().equalsIgnoreCase(entry.getPhoneNumber());


            if (araNamesEquals && areLastNamesEquals && areStreetEquals && areCitiesEquals && areStatesEquals && areZipsEquals && areEmailsEquals && arePhoneNumbersEquals) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Sorts the address book by last name, and if last names are equal, by first name.
     * Sorting is case-insensitive.
     */
    private void sortAddressBook() {
        Collections.sort(ADDRESS_ENTRY_LIST, new Comparator<AddressEntry>() {
            @Override
            public int compare(AddressEntry e1, AddressEntry e2) {
                int lastNameComparison = e1.getLastName().compareToIgnoreCase(e2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                } else {
                    return e1.getName().compareToIgnoreCase(e2.getName());
                }
            }
        });
    }

    /**
     * Searches for address entries by last name or the first letters of the last name.
     * The search is case-insensitive.
     *
     * @param query the search query, which can be a full or partial last name
     * @return a list of AddressEntry objects that match the search query
     */
    private ArrayList<AddressEntry> searchByLastName(String query) {
        ArrayList<AddressEntry> results = new ArrayList<>();

        String contactLastNameLoweredCase;
        String queryLoweredCase = query.toLowerCase();

        for (AddressEntry entry: ADDRESS_ENTRY_LIST) {
            contactLastNameLoweredCase = entry.getLastName().toLowerCase();

            if (contactLastNameLoweredCase.startsWith(queryLoweredCase)) {
                results.add(entry);
            }
        }

        return results;
    }

    /**
     * Displays a message indicating the number of search results found.
     * If no matches are found, indicates this to the user.
     *
     * @param results the list of AddressEntry objects found by the search
     */
    private void messageWhenSearchingAddressEntry(ArrayList<AddressEntry> results) {
        if (results.size() == 0) {
            System.out.println("No matches found.");
        } else if (results.size() == 1) {
            System.out.println("The following contact was found: ");
        } else {
            System.out.println("The following " + results.size() + " contacts were found:");
        }
    }

    /**
     * Prints the list of AddressEntry objects.
     * If the list is empty, indicates this to the user.
     *
     * @param list the list of AddressEntry objects to print
     */
    private void printAdressEntryList(ArrayList<AddressEntry> list) {

        if (list.size() == 0) {
            System.out.println("Nothing here yet");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.print((i+1) + ": ");
                System.out.println(list.get(i));
            }
        }

    }

}