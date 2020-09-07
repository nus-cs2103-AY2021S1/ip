package duke.exceptions;

/**
 * DukeException class to customize exceptions for Duke.
 */
public class DukeException extends Exception {

    /**
     Constructs a new duke exception with the specified detail message.

     * @param message the detail message. The detail message is saved for later
     * retrieval by the Throwable.getMessage() method.
     */
    public DukeException(String message) {
        super(message);
    }
}
