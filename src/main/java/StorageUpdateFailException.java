/**
 * Represents exception thrown when unable to update storage.
 */
public class StorageUpdateFailException extends DukeException {
    StorageUpdateFailException() {
        super("Failed to update tasks to storage. Please try again.");
    }
}
