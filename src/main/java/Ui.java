import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles all elements displayed to the user
 */
public class Ui {
    static final String FORMAT = "\t";
    private final Scanner scan;


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
        System.out.println(FORMAT + "Hello! I'm Jarvis");
        System.out.println(FORMAT + "What can I do for you?");
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
        System.out.println(FORMAT + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints error message
     * @param message Error message from exception
     */
    public void showError(String message) {
        System.out.println(FORMAT + "An error was thrown: " + message);
    }

    /**
     * Formats given String and prints in the UI
     * @param printStr String to be formatted and printed
     */
    public String printf(String printStr) {
        String print = FORMAT + printStr;
        String[] printSplit = print.split("\n");
        for (int i = 1; i < printSplit.length; i++) {
            printSplit[i] = "\t" + printSplit[i];
        }
        return String.join("\n", Arrays.asList(printSplit));
    }
}
