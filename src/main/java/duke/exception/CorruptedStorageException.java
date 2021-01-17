package duke.exception;

/**
 * Encapsulates the exception when the storage is corrupted.
 */
public class CorruptedStorageException extends DukeException {
    /**
     * Initialises a new instance with a warning that the storage is corrupted, followed by the
     * specified detail message.
     *
     * @param message The detail message.
     */
    public CorruptedStorageException(String message) {
        super("Storage is corrupted! " + message);
    }
}
