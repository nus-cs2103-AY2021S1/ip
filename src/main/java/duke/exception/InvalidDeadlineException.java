package duke.exception;

/**
 * Thrown when the input to create a Deadline task is not formatted correctly.
 */
public class InvalidDeadlineException extends DukeException {
    /**
     * Initializes the InvalidDeadlineException object with the error message suggesting the proper format.
     */
    public InvalidDeadlineException() {
        super("Deadline task is poorly formatted.\n    Here is a proper format: " +
                "deadline 'task name' /by 'end time'" +
                "\n      e.g. deadline Exercise /by 2020-12-01 12:00");
    }
}
