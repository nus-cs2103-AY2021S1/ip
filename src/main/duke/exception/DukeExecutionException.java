package duke.exception;

/**
 * Exception to be thrown if an error occurred in executing a Command.
 */
public class DukeExecutionException extends DukeException {
    public DukeExecutionException(String error) {
        super(error);
    }
}
