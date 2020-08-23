package duke.exceptions;

public class EmptyBodyException extends DukeException {

    public EmptyBodyException() {
        super("Body of task not specified",
                "OOPS!!! The description of a task cannot be empty.");
    }
}
