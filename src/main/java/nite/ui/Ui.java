package nite.ui;

import java.util.Scanner;

/**
 * Represents the Ui dealing with interactions with the user.
 */
public class Ui {

    private static final String DIVIDER =
            "___________________________________\n";
    private static final String LOGO =
            "            _____   _____\n"
            + "|\\   |  |     |    |\n"
            + "| \\  |  |     |    |_____\n"
            + "|  \\ |  |     |    |\n"
            + "|   \\|  |     |    |_____\n";
    private static final String GREETING =
            "  Hello! I'm Nite, the Dark Knight,\n"
            + "  Here to help you track your tasks and achieve a great night.\n"
            + "  What can I do for you?\n";
    private static final String GOODBYE =
            "  Good nite! Hope you have a good night's sleep. See you soon!\n";

    private Scanner scanner;

    /**
     * Creates a Ui for interacting with the user.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when user starts up Duke.
     *
     * @return String representation of farewell message.
     */
    public static String showWelcome() {
        return LOGO + DIVIDER + GREETING + DIVIDER;
    }

    /**
     * Displays the farewell message when user exits Duke.
     *
     * @return String representation of farewell message.
     */
    public String showFarewell() {
        return DIVIDER + GOODBYE + DIVIDER;
    }

    /**
     * Displays a formatted error message if a DukeException occurs.
     *
     * @param errorMessage Error message of the DukeException.
     * @return String representation of an error message.
     */
    public String showError(String errorMessage) {
        String enhancedMessage = "  :-( OOPS!!! " + errorMessage + "\n";
        return DIVIDER + enhancedMessage + DIVIDER;
    }

    /**
     * Displays a formatted message showing execution of a command.
     *
     * @param actionMessage Message to be shown to user.
     * @return String representation of a completed action.
     */
    public String showAction(String actionMessage) {
        return DIVIDER + actionMessage + DIVIDER;
    }

    /**
     * Displays a loading error if unable to load tasks from text file.
     *
     * @return String representation of loading error.
     */
    public String showLoadingError() {
        return "Unable to load Tasklist";
    }

    /**
     * Scans for user input.
     * @return String of text that user has input.
     */
    public String readCommand() {
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }
}
