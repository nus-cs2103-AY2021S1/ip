/**
 * An exception class used for errors caused by inappropriate user input. Displays a message.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param msg the message to be displayed.
     */
    DukeException(String msg) {
        super("☹ OOPS!!! " + msg);
    }
}