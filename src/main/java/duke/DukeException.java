package duke;

/**
 * Thrown to indicate that an exception with Duke has occurred.
 *
 * @author YanCheng
 */

public class DukeException extends Exception {
    /**
     * Creates a DukeException.
     * @param str Text to be displayed for the error
     */
    public DukeException(String str) {
        super(str);
    }
}
