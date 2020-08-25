package duke.exception;

/**
 * Represents an exception thrown when Storage path is invalid.
 */
public class DukeInvalidStoragePathException extends DukeStorageException {
    public DukeInvalidStoragePathException() {
        super("☹ OOPS!!! Invalid file path!");
    }
}
