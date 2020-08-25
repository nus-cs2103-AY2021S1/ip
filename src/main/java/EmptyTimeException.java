/**
 * Represents a DukeException when a user does not input a time
 * for a Task.
 */
public class EmptyTimeException extends DukeException {
    public EmptyTimeException(String message) {
        super(message);
    }
}
