/**
 * Represents a DukeException when a user does not input a proper task.
 */
public class UnknownTaskException extends DukeException {
    public UnknownTaskException(String message) {
        super(message);
    }
}
