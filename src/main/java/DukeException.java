/**
 * Represents a DukeException object which is a subclass of java Exception.
 * For printing of Duke program related error messages.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException object.
     * It is used for Duke error printing.
     *
     * @param String message is the description of the Duke errors.
     */
    public DukeException(String message) {
        super(message);
    }
}
