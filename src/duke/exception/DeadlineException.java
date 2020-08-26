package duke.exception;

/**
 * The exception thrown when the user does not input any description
 * for the deadline's task name or does not use /by to specify the
 * task's date.
 */
public class DeadlineException extends DukeException {

    /**
     * Constructs a DeadlineException with default message.
     * The message is "OOPS!!! The description or date of a deadline cannot be empty."
     */
    public DeadlineException() {
        super("OOPS!!! The description or date of a deadline cannot be empty.");
    }
}
