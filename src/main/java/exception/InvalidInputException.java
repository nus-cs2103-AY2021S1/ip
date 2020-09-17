package exception;

/**
 * Triggers when a user inputs an invalid command.
 */
public class InvalidInputException extends DukeException {

    /**
     * Initialises the exception object that warns
     * users of the incorrect input.
     *
     * @param message Informs the users of the error.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
