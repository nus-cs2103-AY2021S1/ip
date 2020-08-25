/**
 * Represents a DukeException when a user does not input an appropriate
 * command.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
