package duke.exception;

/**
 * Thrown when the csv file fails to update properly.
 */
public class FileUpdateFailException extends DukeException {

    /**
     * Initializes the FileUpdateFailException.
     */
    public FileUpdateFailException() {
        super("Failed to update tasks in csv file.");
    }
}
