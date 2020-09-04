package raythx98.grandma.exception;

/**
 * Checked exception as a result of an unknown command.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
