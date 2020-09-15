package duke.exceptions;

/**
 * Thrown when a duplicate Task is detected.
 */
public class DuplicateException extends DukeException {
    public DuplicateException() {
        super("There appears to be a duplicate task");
    }
}
