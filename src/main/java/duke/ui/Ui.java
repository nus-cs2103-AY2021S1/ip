package duke.ui;

/**
 * Text UI of the application
 */
public class Ui {
    public static final String DIVIDER = "_______________________________________________________";
    private static final String SPACING = "         ";

    /**
     * Displays a welcome message to the user.
     */
    public void greeting() {
        String logo = "Dash";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you today?");
        System.out.println(DIVIDER);
    }

    /**
     * Scans for user input from the user.
     */
    public void getUserCommand() {
        System.out.println("Please enter a command:");

    }

    /**
     * Formats the message printed by the program around
     * dividers.
     * @param messages The messages of each line in the block of
     *                 messages to be printed.
     */
    public void printMessage(String... messages) {
        for (String message : messages) {
            System.out.println(SPACING + message);
        }
        System.out.println(DIVIDER);
    }
}
