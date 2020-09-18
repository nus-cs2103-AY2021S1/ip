package duke.exception;

/**
 * Thrown when there are problems of string format.
 */
public class IncorrectFormatException extends DukeException {

    public IncorrectFormatException(String description) {
        super(description);
    }
}
