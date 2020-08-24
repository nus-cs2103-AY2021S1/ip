package exception;

public class StorageException extends Exception {
    public StorageException(String message) {
        super("StorageException: " + message);
    }
}
