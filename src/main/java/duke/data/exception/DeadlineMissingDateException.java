package duke.data.exception;

/**
 * Exception thrown when the Deadline has a missing valid date.
 */
public class DeadlineMissingDateException extends DukeException {

    public DeadlineMissingDateException() {
        super("Date is missing for deadline, please include a date preceded by /by");
    }
}
