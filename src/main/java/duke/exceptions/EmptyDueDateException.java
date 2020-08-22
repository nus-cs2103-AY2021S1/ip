package duke.exceptions;

public class EmptyDueDateException extends DukeException {

    public EmptyDueDateException() {
        super("OOPS! The due date of deadline cannot be empty!");
    }

}
