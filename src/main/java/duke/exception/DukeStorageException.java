package duke.exception;

/**
 * Represents an exception thrown when dealing with Storage.
 */
public class DukeStorageException extends DukeException {
    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    DukeStorageException(String message) {
        super(message);
    }
}
