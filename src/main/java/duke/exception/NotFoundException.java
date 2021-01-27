package duke.exception;

public class NotFoundException extends DukeException {
    /**
     * Constructs a task not found exception.
     */
    public NotFoundException() {
        super(" OOPS!!! The task is not found. \n     Please try again.");
    }
}
