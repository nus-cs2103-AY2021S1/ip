package duke.exception;

public class ReadFailedException extends StorageException {
    public ReadFailedException(String item) {
        super(String.format("Failed to read %s!", item));
    }
}
