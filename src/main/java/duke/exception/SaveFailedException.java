package duke.exception;

/**
 * The Save failed exception.
 */
public class SaveFailedException extends StorageException {
    /**
     * Instantiates a new Save failed exception.
     *
     * @param item the item.
     */
    public SaveFailedException(String item) {
        super(String.format("Failed to save %s!", item));
    }
}
