package adress.data;

/* acá están cada list.Permite varias operaciones como buscar/encontrar, agregar y eliminar AddressEntries. Es el libro
que contiene páginas (AdressEntry)
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class AddressBook {

    private static AddressBook instance;
    private ArrayList<AddressEntry> addressEntries = new ArrayList<>();

    private InputReader reader = new InputReader();

    private AddressBook() {}

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

    public void addAddressEntry(AddressEntry newEntry) {
        if (isNotDuplicated(newEntry)) {
            addressEntries.add(newEntry);
            sortAddressBook();
        }
    }

    public void addAdressEntriesFromFile() {

        try {
            String filePath = reader.readData("Enter the file's name:", this::isATxtFile);

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

    public ArrayList<AddressEntry> searchAddressEntry() {
        String query = reader.readData("Enter last name or first letters:", this::isDataNotEmpty);
        ArrayList<AddressEntry> results = this.searchByLastName(query);
        messageWhenSearchingAddressEntry(results);
        printAdressEntryList(results);
        return results;
    }


    public void deleteAddressEntry() {
        ArrayList<AddressEntry> results = searchAddressEntry();

        if (results.size() > 0) {
            String stringSelectedEntry;

            int selectedEntry;

            do {
                stringSelectedEntry = reader.readData("Enter the number you want to delete:", this::isNumericDataValid);
                selectedEntry = Integer.parseInt(stringSelectedEntry);
                selectedEntry-=1;

            } while (!isBetweenEntrySize(selectedEntry, results));

            int entryIndex = lookingForExistingEntryByIndex(results.get(selectedEntry));

            String wantToDelete = reader.readData("Enter 'y' to delete or 'n' to go back to menu", this::isYOrN).toLowerCase();

            if (wantToDelete.equals("y")) {
                addressEntries.remove(entryIndex);
            }
        }

    }

    public void showAddressBook() {
        printAdressEntryList(addressEntries);
    }

    public ArrayList<AddressEntry> getAddressEntries() {
        return addressEntries;
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

    private boolean isBetweenEntrySize(int selection, ArrayList<AddressEntry> list) {
        return selection < list.size() && selection > -1;
    }

    private boolean isNotDuplicated(AddressEntry entry) {
        return lookingForExistingEntryByIndex(entry) == -1;
    }

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

        for (AddressEntry existingEntry : addressEntries) {
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
        Collections.sort(addressEntries, new Comparator<AddressEntry>() {
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

    private ArrayList<AddressEntry> searchByLastName(String query) {
        ArrayList<AddressEntry> results = new ArrayList<>();

        String contactLastNameLoweredCase;
        String queryLoweredCase = query.toLowerCase();

        for (AddressEntry entry: addressEntries) {
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
