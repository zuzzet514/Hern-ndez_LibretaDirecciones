package adress.data;
import java.util.Scanner;
import java.util.function.Predicate;

public class InputReader {
    private String data;
    private String rawData;
    private Scanner input = new Scanner(System.in);

    public InputReader() {
    }

    private void invalidDataMessage() {
        System.out.println("Invalid data input. Please try again.");
    }

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
