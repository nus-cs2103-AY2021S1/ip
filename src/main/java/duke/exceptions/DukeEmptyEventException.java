package duke.exceptions;

public class DukeEmptyEventException extends DukeException {

    public final static String ERROR_EMPTY_EVENT_TASK = "⚠⚠⚠ The description of a 'event' cannot be empty.";

    public DukeEmptyEventException() {
        super(ERROR_EMPTY_EVENT_TASK);
    }
}
