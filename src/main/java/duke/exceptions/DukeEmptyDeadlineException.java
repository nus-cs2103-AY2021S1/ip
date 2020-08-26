package duke.exceptions;

public class DukeEmptyDeadlineException extends DukeException {
    public final static String ERROR_EMPTY_DEADLINE_TASK = "⚠⚠⚠ The description of a 'deadline' cannot be empty.";

    public DukeEmptyDeadlineException() {
        super(ERROR_EMPTY_DEADLINE_TASK);
    }
}
