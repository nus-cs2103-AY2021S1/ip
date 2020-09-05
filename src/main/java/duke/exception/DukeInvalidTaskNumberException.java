package duke.exception;

/**
 * Represents the exception when a task number is invalid.
 */
public class DukeInvalidTaskNumberException extends DukeException {

    private static final String ERROR_MESSAGE = "%s is an invalid task number! "
            + "Please enter a valid task number :)\n";

    public DukeInvalidTaskNumberException(String taskNumber) {
        super(String.format(ERROR_MESSAGE, taskNumber));
    }
}
