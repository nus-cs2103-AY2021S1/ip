package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the task is already marked as done.
 */
public class DukeAlreadyDoneException extends DukeException {

    public static final String ERROR_ALREADY_DONE = "This task is already done!";

    /**
     * DukeAlreadyDoneException constructor.
     */
    public DukeAlreadyDoneException() {
        super(ERROR_ALREADY_DONE);
    }
}
