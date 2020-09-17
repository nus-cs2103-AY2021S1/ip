package duke.exceptions;

/**
 * Represents exception thrown when unable to update storage.
 */
public class StorageUpdateFailException extends DukeException {
    public StorageUpdateFailException() {
        super("Oh no, failed to update tasks to storage. Please try again.");
    }
}
