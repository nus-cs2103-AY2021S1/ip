package duke.ui;

import java.util.Scanner;

/**
 * Represents the Ui dealing with interactions with the user.
 */
public class Ui {

    private static final String DIVIDER =
            "\t____________________________________________________________\n";
    private static final String LOGO =
            " _____     __   __   ________   _________\n"
            + "|     \\   |  | |  | |__    __| |   ______|\n"
            + "|  |\\  \\  |  | |  |    |  |    |  |______\n"
            + "|  | \\  \\ |  | |  |    |  |    |   ______|\n"
            + "|  |  \\  \\|  | |  |    |  |    |  |______\n"
            + "|__|   \\_____| |__|    |__|    |_________|\n";
    private static final String GREETING =
            "\t Hello! I'm Nite, the Dark Knight,\n"
            + "\t Here to help you track your tasks and achieve a great night.\n"
            + "\t What can I do for you?\n";
    private static final String GOODBYE =
            "\t Good nite! Hope you have a good night's sleep. See you soon!\n";

    private Scanner scanner;

    /**
     * Creates a Ui for interacting with the user.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when user starts up duke.Duke.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(DIVIDER + GREETING + DIVIDER);
    }

    /**
     * Displays the farewell message when user exits duke.Duke.
     */
    public void showFarewell() {
        System.out.print(DIVIDER + GOODBYE + DIVIDER);
    }

    /**
     * Displays a formatted error message if a DukeException occurs.
     *
     * @param errorMessage Error message of the DukeException.
     */
    public void showError(String errorMessage) {
        String enhancedMessage = "\t :-( OOPS!!! " + errorMessage + "\n";
        System.out.println(DIVIDER + enhancedMessage + DIVIDER);
    }

    /**
     * Displays a formatted message showing execution of a command.
     *
     * @param actionMessage Message to be shown to user.
     */
    public void showAction(String actionMessage) {
        System.out.println(DIVIDER + actionMessage + DIVIDER);
    }

    /**
     * Displays a loading error if unable to load tasks from text file.
     */
    public void showLoadingError() {
        System.out.println("Unable to load Tasklist");
    }

    /**
     * Scans for user input.
     * @return String of text that user has input.
     */
    public String readCommand() {
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }
}
