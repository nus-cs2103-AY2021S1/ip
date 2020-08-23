package duke.exception;

public class SaveFailedException extends StorageException {
    public SaveFailedException(String item) {
        super(String.format("Failed to save %s!", item));
    }
}
