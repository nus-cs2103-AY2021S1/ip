package duke.exception;

public class InvalidDescriptionException extends DukeException {

    public InvalidDescriptionException() {
        super("☹ OOPS!!! The description for a task command cannot be empty!");
    }
}
