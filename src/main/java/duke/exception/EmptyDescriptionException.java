package duke.exception;

/**
 * Checked exception as a result of an empty description.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
