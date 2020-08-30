package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the description for the deadline command is empty.
 */
public class DukeEmptyDeadlineException extends DukeException {
    public static final String ERROR_EMPTY_DEADLINE_TASK = "⚠⚠⚠ The description of a 'deadline' cannot be empty.";

    /**
     * DukeEmptyDeadlineException constructor.
     */
    public DukeEmptyDeadlineException() {
        super(ERROR_EMPTY_DEADLINE_TASK);
    }
}
