package duke;

/**
 * Class for displaying output to user.
 */
public class Ui {
    /**
     * Prints message with 4 indentation.
     * @param message message to be printed
     */
    public static void printWith4Indent(String message) {
        System.out.println(StringConstants.FOUR_SPACES + message);
    }

    /**
     * Prints message with 6 indentation.
     * @param message message to be printed
     */
    public static void printWith6Indent(String message) {
        System.out.println(StringConstants.SIX_SPACES + message);
    }

    /**
     * Prints new line.
     */
    public static void printNewLine() {
        System.out.println();
    }

    /**
     * Prints message between 2 lines.
     * @param message message to be printed
     */
    public static void printMessageBetweenLines(String message) {
        printWith4Indent(StringConstants.LINE);
        printWith6Indent(message);
        printWith4Indent(StringConstants.LINE);
    }

    /**
     * Prints array of messages between 2 lines.
     * @param messages array of messages to be printed
     */
    public static void printMessagesBetweenLines(String[] messages) {
        printWith4Indent(StringConstants.LINE);
        for (String message: messages) {
            printWith6Indent(message);
        }
        printWith4Indent(StringConstants.LINE);
    }

    /**
     * Prints exception message between 2 lines.
     * @param exception exception to be printed
     */
    public static void printExceptionBetweenLines(Exception exception) {
        printMessageBetweenLines("Error: " + exception.getMessage());
    }

    /**
     * Prints greeting to user.
     */
    public static void printGreeting() {
        printMessagesBetweenLines(StringConstants.GREETING_MESSAGES);
    }
}
