package duke.exception;

/**
 * Represents an exception thrown because of invalid Stage file data.
 */
public class DukeInvalidDataException extends DukeStorageException {
    public DukeInvalidDataException(String message) {
        super(message);
    }
}
