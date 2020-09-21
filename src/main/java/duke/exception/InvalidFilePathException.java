package duke.exception;

/**
 * Represents an InvalidFilePathException and handles the exceptions related
 * to invalid file paths.
 */
public class InvalidFilePathException extends Exception {

    /**
     * Constructs an InvalidFilePathException.
     *
     * @param message Error message to be sent to the user.
     */
    public InvalidFilePathException(String message) {
        super(message);
    }
}
