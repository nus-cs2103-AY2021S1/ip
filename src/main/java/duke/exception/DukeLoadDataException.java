package duke.exception;

/**
 * Represents the exception when the data could not be loaded.
 */
public class DukeLoadDataException extends DukeException {

    private static final String ERROR_MESSAGE = "Error loading tasks, starting up with no saved records...\n";

    public DukeLoadDataException() {
        super(ERROR_MESSAGE);
    }
}
