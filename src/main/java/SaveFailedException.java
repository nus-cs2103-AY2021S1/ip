public class SaveFailedException extends StorageException {
    SaveFailedException(String item) {
        super(String.format("Failed to save %s!", item));
    }
}
