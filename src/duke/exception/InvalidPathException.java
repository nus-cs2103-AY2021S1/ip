package duke.exception;

/**
 * Indicates an invalid file path.
 */
public class InvalidPathException extends StorageException {
    /**
     * Creates a new InvalidPathException with no detail message.
     */
    public InvalidPathException() {
        super("Invalid path!! Closing program");
    }
}