package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the description for the deadline command is empty.
 */
public class DukeEmptyDeadlineException extends DukeException {

    public static final String ERROR_EMPTY_DEADLINE_TASK = "You fur-get some deadline details.";


    /**
     * DukeEmptyDeadlineException constructor.
     */
    public DukeEmptyDeadlineException() {
        super(ERROR_EMPTY_DEADLINE_TASK);
    }
}
