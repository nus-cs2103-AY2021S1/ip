package duke.exception;

/**
 * The Read failed exception.
 */
public class ReadFailedException extends StorageException {
    /**
     * Instantiates a new Read failed exception.
     *
     * @param item the item read.
     */
    public ReadFailedException(String item) {
        super(String.format("Failed to read %s!", item));
    }
}
