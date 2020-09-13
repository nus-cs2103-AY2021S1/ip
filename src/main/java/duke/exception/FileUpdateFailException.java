package duke.exception;

/**
 * Thrown when the csv file fails to update properly.
 */
public class FileUpdateFailException extends DukeException {

    private static final String FILE_UPDATE_FAIL_MESSAGE = "Error in updating .csv file in storage.";

    /**
     * Initializes the FileUpdateFailException.
     */
    public FileUpdateFailException() {
        super(FILE_UPDATE_FAIL_MESSAGE);
    }
}
