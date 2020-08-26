package duke.exception;

// Signals any invalid command issued by the user.
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
