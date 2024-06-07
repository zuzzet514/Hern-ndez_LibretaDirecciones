package adress.data;

import java.util.Scanner;
import java.util.function.Predicate;

/**
 * The InputReader class provides functionality for reading and validating user input.
 */
public class InputReader {
    private String data;
    private String rawData;
    private Scanner input = new Scanner(System.in);

    /**
     * Default constructor for creating an InputReader object.
     */
    public InputReader() {
    }

    /**
     * Displays an error message indicating that the input data is invalid.
     */
    private void invalidDataMessage() {
        System.out.println("Invalid data input. Please try again.");
    }

    /**
     * Reads and validates user input based on the provided prompt message and validation predicate.
     *
     * @param promptMessage the message to prompt the user for input
     * @param validator a Predicate to validate the user input
     * @return the validated input data as a string
     */
    public String readData(String promptMessage, Predicate<String> validator){
        System.out.println(promptMessage);
        this.rawData = input.nextLine();

        while (!validator.test(rawData)) {
            invalidDataMessage();
            System.out.println(promptMessage);
            rawData = input.nextLine();
        }

        this.data = rawData;
        return data;
    }
}