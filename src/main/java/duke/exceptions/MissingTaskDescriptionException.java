package duke.exceptions;

public class MissingTaskDescriptionException extends DukeException {

    public MissingTaskDescriptionException() {
        super("☹ OOPS!!! The description of a task cannot be empty.");
    }
}
