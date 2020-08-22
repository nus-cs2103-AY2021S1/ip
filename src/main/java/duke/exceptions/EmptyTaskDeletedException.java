package duke.exceptions;

public class EmptyTaskDeletedException extends IllegalArgumentException {

    public EmptyTaskDeletedException() {
        super("OOPS! duke.tasks.Task deleted cannot be empty!");
    }
}
