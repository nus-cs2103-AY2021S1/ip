package duke.exception;

/**
 * Represents an exception thrown because of invalid Stage file data.
 */
public class DukeInvalidDataException extends DukeStorageException {
    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public DukeInvalidDataException(String message) {
        super(message);
    }
}
