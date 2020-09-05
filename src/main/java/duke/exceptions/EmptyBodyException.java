package duke.exceptions;

/**
 * Exception thrown when user fails to provide a description of the Task.
 */
public class EmptyBodyException extends DukeException {

    public EmptyBodyException() {
        super("Body of task not specified",
                "OOPS!!! The description of a task cannot be empty.");
    }
}
