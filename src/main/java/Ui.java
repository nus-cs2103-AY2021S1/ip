/**
 * Handles printing of messages.
 */
public class Ui {
    /**
     * Prints an error message with the associated formatting.
     *
     * @param msg The message to be printed.
     */
    public static String errorMsg(String msg) {
        return "⚠ " + msg + " ⚠\n";
    }

    /**
     * Prints a message with the associated formatting.
     *
     * @param msg The message to be printed.
     */
    public static String print(String msg) {
        return "✰ " + msg + " ✰\n";
    }
}
