package duke.exception;

public class InvalidPathException extends StorageException {
    public InvalidPathException() {
        super("Invalid path!! Closing program");
    }
}
