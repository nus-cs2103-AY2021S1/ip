import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Duke {

    private static ResourceBundle strings;
    private static Scanner scanner;

    private static void initializeDuke() {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
        scanner = new Scanner(System.in);
    }

    private static void printWithDecoration(String string) {
        String lineDecoration = "\t" + "_".repeat(60);
        System.out.println(lineDecoration);
        System.out.println("\t" + string);
        System.out.println(lineDecoration);
    }

    public static void main(String[] args) {

        initializeDuke();

        printWithDecoration(strings.getString("output.greeting"));

        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals(strings.getString("command.bye"))) {
                break;
            }

            printWithDecoration(input);
        }

        printWithDecoration(strings.getString("output.bye"));
    }

}
