package duke.exception;

/**
 * Represents the exception when the data could not be saved.
 */
public class DukeSaveDataException extends DukeException {

    private static final String ERROR_MESSAGE = "An error occurred, unable to save tasks to file.";

    public DukeSaveDataException() {
        super(ERROR_MESSAGE);
    }
}
