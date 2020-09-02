package duke.exception;

/**
 * Thrown when creating a ToDo with no description.
 */
public class NoDescriptionException extends DukeException {

    public NoDescriptionException(String errMessage) {
        super(errMessage);
    }
}
