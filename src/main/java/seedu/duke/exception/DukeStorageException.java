package seedu.duke.exception;

/**
 * Exception that encapsulates what happens when there is an error in handling the text file.
 */
public class DukeStorageException extends DukeException {
    public DukeStorageException(String message) {
        super(message);
    }
}
