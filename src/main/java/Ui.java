import java.io.IOException;

public class Ui {
    public static final String NEW_LINE = "\n";
    public static final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    public static final String PADDING = "      ";

    private static final String MESSAGE_TEMPLATE = HORIZONTAL_LINE + NEW_LINE + PADDING + "%s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    public static final String MESSAGE_TEMPLATE_VERBAL = HORIZONTAL_LINE + NEW_LINE + PADDING + "Deuk: %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private static final String MESSAGE_TEMPLATE_ERROR = HORIZONTAL_LINE + NEW_LINE + PADDING
            + "OOPS!!! %s"
//            + "☹ OOPS!!! %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;

    /**
     * Prints the given message to the user.
     * @param msg Message to be printed to the user.
     */
    public static void print(String msg) {
        System.out.printf(MESSAGE_TEMPLATE, msg);
    }

    /**
     * Prints the error message to the user.
     * @param errMsg Error message to be printed to the user.
     */
    public static void printError(String errMsg) {
        System.out.printf(MESSAGE_TEMPLATE_ERROR, errMsg);
    }

    /**
     * Prints the given message in the following format "Deuk: [Message]" to the user.
     * @param msg Message to be printed to the user.
     */
    public static void printVerbal(String msg) {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, msg);
    }

    /**
     * Prints the hello message to the user.
     * @param logo Logo of the Deuk Programme.
     * @param introMessage Welcome message to the user.
     */
    public static void sayHello(String logo, String introMessage) {
        System.out.printf(logo + NEW_LINE + MESSAGE_TEMPLATE_VERBAL, introMessage);
    }

    /**
     * Prints the farewell message and saves tasks in task list to disk.
     * @param storage Storage instance used by the Duke instance.
     * @param tasks TaskList instance used by the Duke instance.
     * @see Storage#saveTasksToDisk(TaskList)
     */
    public static void sayGoodbye(Storage storage, TaskList tasks) {
        try {
            storage.saveTasksToDisk(tasks);
        } catch (IOException ex) {
            Ui.printError(ex.getMessage());
        }
        Ui.printVerbal("Goodbye, hope to see you again!");
    }
}
