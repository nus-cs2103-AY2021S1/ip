package duke.exception;

import java.io.IOException;

/**
 * Indicates when an error occurs in the local storage due to an invalid file path.
 */
public class StorageException extends IOException {
    /**
     * Creates a new StorageException with the specified error message.
     *
     * @param message Error message to be displayed to the user via the user interface.
     */
    StorageException(String message) {
        super(message);
    }
}
