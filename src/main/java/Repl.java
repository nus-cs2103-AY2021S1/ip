import java.util.Scanner;

/**
 * A read-eval-print loop (REPL) that reads in a command from the user, executes it, and prints out the result.
 */
public class Repl {
    // Formatting
    /** Number of spaces to prefix each line with. */
    private static final int LEFT_PADDING_SIZE = 4;
    /** Number of underscores each divider should be made up of. */
    private static final int DIVIDER_LENGTH = 60;

    // Strings
    /** Message to be displayed on start-up. */
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    /** Message to be displayed on exit. */
    private static final String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";

    /** {@code Scanner} object which reads in user input. */
    private static final Scanner scanner = new Scanner(System.in);
    /** {@code PrettyPrinter} object for formatting the REPL output. */
    private static final PrettyPrinter prettyPrinter = new PrettyPrinter(LEFT_PADDING_SIZE, DIVIDER_LENGTH);

    /**
     * Runs the REPL.
     */
    public static void run() {
        prettyPrinter.print(WELCOME_MESSAGE);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            switch (command) {
                case "bye":
                    prettyPrinter.print(FAREWELL_MESSAGE);
                    return;
                default:
                    prettyPrinter.print(command);
                    break;
            }
        }
    }
}
