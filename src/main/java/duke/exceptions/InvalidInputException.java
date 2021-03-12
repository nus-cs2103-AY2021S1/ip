package duke.exceptions;

/**
 * InvalidInputException is thrown when a command has an incorrect
 * input.
 */
public class InvalidInputException extends DukeException {

    /**
     Constructs a new invalid input exception with the specified detail message.

     * @param message the detail message. The detail message is saved for later
     * retrieval by the Throwable.getMessage() method.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
