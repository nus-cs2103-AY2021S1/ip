package duke.exception;

/**
 * Represents an exception when user input an invalid Task number.
 */
public class InvalidTaskNumberException extends DukeException {

    /**
     * Constructs an InvalidTaskNumberException.
     *
     * @param errorMessage ErrorMessage to be shown.
     */
    public InvalidTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}
