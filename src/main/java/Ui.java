import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handles all elements displayed to the user
 */
public class Ui {
    private Scanner scan;
    static private String format = "\t";

    Ui() {
        this.scan = new Scanner(System.in);
    }

    /**
     * Prints the welcome message
     */
    void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(format + "Hello! I'm Duke");
        System.out.println(format + "What can I do for you?");
    }

    /**
     * Reads the user input line
     * @return String of raw input
     */
    String readCommand() {
        return this.scan.nextLine();
    }

    /**
     * Prints divider line
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Prints goodbye message
     */

    public void goodbye() {
        System.out.println(format + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints error message
     * @param message Error message from exception
     */

    public void showError(String message) {
        System.out.println(format + "An error was thrown: " + message);
    }

    /**
     * Formats given String and prints in the UI
     * @param printStr String to be formatted and printed
     */
    public void printf(String printStr) {
        String print = format + printStr;
        String[] printSplit = print.split("\n");
        for (int i = 1; i < printSplit.length; i++) {
            printSplit[i] = "\t" + printSplit[i];
        }
        System.out.println(Arrays.asList(printSplit).stream().collect(Collectors.joining("\n")));
    }
}
