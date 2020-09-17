package duke.exception;

/**
 * Represents an exception which occurs when the user provides an invalid or unrecognised command.
 */
public class InvalidFunctionException extends DukeException {

    /**
     * Creates and initialises a new InvalidFunctionException with a specified error message.
     *
     * @param message String containing the specified error message.
     */
    public InvalidFunctionException(String message) {
        super(message + "\nType 'help' for a list of all my functions and their commands.");
    }
}
