package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the description for the event command is empty.
 */
public class DukeEmptyEventException extends DukeException {

    public static final String ERROR_EMPTY_EVENT_TASK = "You fur-get some event details.";


    /**
     * DukeEmptyEventException constructor.
     */
    public DukeEmptyEventException() {
        super(ERROR_EMPTY_EVENT_TASK);
    }
}
