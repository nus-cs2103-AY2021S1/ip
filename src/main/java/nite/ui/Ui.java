package nite.ui;

/**
 * Represents the Ui dealing with interactions with the user.
 */
public class Ui {

    private static final String LOGO =
            "            _____   _____\n"
            + "|\\   |  |     |    |\n"
            + "| \\  |  |     |    |_____\n"
            + "|  \\ |  |     |    |\n"
            + "|   \\|  |     |    |_____\n";
    private static final String GREETING =
            "  Hello! I'm Nite, the Dark Knight,\n"
            + "  Here to help you track your tasks and achieve a great night.\n"
            + "  What can I do for you?"
            + "  Type \"help\" for a list of commands to get started!\n";
    private static final String GOODBYE =
            "  Good nite! Hope you have a good night's sleep. See you soon!\n";

    /**
     * Creates a Ui for interacting with the user.
     */
    public Ui() {
    }

    /**
     * Displays the welcome message when user starts up Duke.
     *
     * @return String representation of farewell message.
     */
    public static String showWelcome() {
        return LOGO + GREETING;
    }

    /**
     * Displays the farewell message when user exits Duke.
     *
     * @return String representation of farewell message.
     */
    public String showFarewell() {
        return GOODBYE;
    }

    /**
     * Displays a formatted error message if a DukeException occurs.
     *
     * @param errorMessage Error message of the DukeException.
     * @return String representation of an error message.
     */
    public String showError(String errorMessage) {
        assert !errorMessage.isEmpty() : "Message to be displayed should not be empty.";
        return "  :-( OOPS!!! " + errorMessage + "\n";
    }

    /**
     * Displays a formatted message showing execution of a command.
     *
     * @param actionMessage Message to be shown to user.
     * @return String representation of a completed action.
     */
    public String showAction(String actionMessage) {
        assert !actionMessage.isEmpty() : "Message to be displayed should not be empty.";
        return actionMessage;
    }
}
