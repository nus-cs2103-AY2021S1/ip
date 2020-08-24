package duke.exceptions;

/** Thrown to indicate that the user input delete without the task. */
public class EmptyTaskDeletedException extends DukeException {

    /** Constructs an EmptyTaskDeletedException with the relevant detail message. */
    public EmptyTaskDeletedException() {
        super("OOPS! Task deleted cannot be empty!");
    }
}
