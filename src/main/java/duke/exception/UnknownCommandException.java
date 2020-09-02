package duke.exception;

/**
 * Thrown when a command entered is not in the DukeCommandSet.
 */
public class UnknownCommandException extends DukeException {

    public UnknownCommandException(String errMessage) {
        super(errMessage);
    }
}
