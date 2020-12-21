package duke.exception;

/**
 * Indicates an error occurred when saving the task list due to an invalid file path
 */
public class SaveToStorageErrorException extends StorageException {
    /**
     * Constructs a new SaveToStorageErrorException with no detail message.
     */
    public SaveToStorageErrorException() {
        super("OOPS! Something went wrong when saving your tasklist :( \n--Save unsuccessful--");
    }
}
