package duke.storage;

/**
 * Represents an exception that happened while storing the file.
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }
}
