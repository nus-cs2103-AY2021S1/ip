public class ReadFailedException extends StorageException {
    ReadFailedException(String item) {
        super(String.format("Failed to read %s!", item));
    }
}
