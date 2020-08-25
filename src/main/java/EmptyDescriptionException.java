/**
 * Represents a DukeException when a user does not input a description
 * for a Task.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
