import java.io.IOException;

public class StorageException extends IOException {
    StorageException(String message) {
        super(message);
    }
}
