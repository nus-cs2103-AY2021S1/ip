package duke.exceptions;

/**
 * InvalidFileException is thrown when the file fails to save or load.
 */
public class InvalidFileException extends DukeException {

    /**
     Constructs a new invalid file exception with the specified detail message.

     * @param message the detail message. The detail message is saved for later
     * retrieval by the Throwable.getMessage() method.
     */
    public InvalidFileException(String message) {
        super(message);
    }
}
