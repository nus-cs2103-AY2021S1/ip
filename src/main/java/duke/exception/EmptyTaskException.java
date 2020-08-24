package duke.exception;

public class EmptyTaskException extends DukeException {
    public EmptyTaskException() {
        super("OOPS! The description of a task cannot be empty");
    }
}
