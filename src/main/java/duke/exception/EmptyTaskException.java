package duke.exception;

/** An exception when the AddTaskCommand given has no description. */
public class EmptyTaskException extends DukeException {

    /** Constructs an EmptyTaskException. */
    public EmptyTaskException() {
        super("OOPS! The description of a task cannot be empty");
    }
}
