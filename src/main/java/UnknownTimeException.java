/**
 * Represents a DukeException when a user does not input a time
 * for a Task that requires a time.
 */
public class UnknownTimeException extends DukeException {
    public UnknownTimeException(String message) {
        super(message);
    }
}
