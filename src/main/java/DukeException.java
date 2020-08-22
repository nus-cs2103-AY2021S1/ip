/**
 * Thrown to indicate that an exception with duke has occurred.
 *
 * @author YanCheng
 */

public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param str Text to be displayed for the error
     */
    public DukeException(String str) {
        super(str);
    }
}
