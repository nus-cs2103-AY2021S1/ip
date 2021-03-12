package duke.exception;

/**
 * Represents the exception when the task number is missing.
 */
public class DukeMissingTaskNumberException extends DukeException {

    private static final String ERROR_MESSAGE = "Missing task number! "
            + "Please ensure to key in the task number :)\n";

    public DukeMissingTaskNumberException() {
        super(ERROR_MESSAGE);
    }

}
