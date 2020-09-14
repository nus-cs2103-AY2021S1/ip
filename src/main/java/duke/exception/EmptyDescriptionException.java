package duke.exception;

/**
 * Represents the exception that is thrown when the description
 * of "todo", "deadline" and "event" is not filled.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructor of the EmptyDescriptionException class.
     *
     * @param errorMessage error message to be thrown.
     */
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }

}
