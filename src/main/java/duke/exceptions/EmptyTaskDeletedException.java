package duke.exceptions;

public class EmptyTaskDeletedException extends DukeException {

    public EmptyTaskDeletedException() {
        super("OOPS! Task deleted cannot be empty!");
    }
}
