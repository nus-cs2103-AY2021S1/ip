package duke.exception;

/**
 * Checked exception as a result of an invalid index.
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }
}
