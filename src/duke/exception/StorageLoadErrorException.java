package duke.exception;

/**
 * Indicates the saved task list is unable to load due to an invalid file path.
 */
public class StorageLoadErrorException extends StorageException {
    /**
     * Creates a new StorageLoadErrorException with no specified detail message.
     */
    public StorageLoadErrorException() {
        super("Sorry but I am unable to load the saved tasklist. "
                + "\nAny changes to your tasklist will not be saved :(");
    }
}
