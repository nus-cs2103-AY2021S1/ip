/**
 * Handles printing of messages.
 */
public class Ui {
    /**
     * Prints an error message with the associated formatting.
     * @param msg The message to be printed.
     */
    public static void errorMsg(String msg) {
        System.out.println("⚠ " + msg + " ⚠");
    }

    /**
     * Prints a message with the associated formatting.
     * @param msg The message to be printed.
     */
    public static void print(String msg) {
        System.out.println("✰ " + msg + " ✰");
    }
}
