package duke;

/**
 * Represents the custom duke exception. This exception is thrown when specific errors relating to the application occurs.
 */
public class DukeException extends Exception {
    /**
     * Creates a duke exception.
     *
     * @param message Error message that describes the error which occured.
     */
    public DukeException(String message) {
        super(message);
    }
}
