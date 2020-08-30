/**
 * Represents a Ui to print messages to interact with User.
 */
public class Ui {
    public static void messagePrint(String msg) {
        // Add indentation for new lines
        msg = msg.replace("\n", "\n    ");
        msg = ("    ____________________________________________________________\n"
                + "    " + msg + "\n"
                + "    ____________________________________________________________\n");
        System.out.printf(msg);
    }
}
