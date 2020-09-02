package rogue.storage.exceptions;

/**
 * Represents an error when saving to or loading from save file.
 */
public class StorageException extends Exception {
    public StorageException(String description) {
        super(description);
    }
}
