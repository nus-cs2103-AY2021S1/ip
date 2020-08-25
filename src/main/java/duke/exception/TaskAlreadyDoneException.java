package duke.exception;

public class TaskAlreadyDoneException extends DukeException {
    public TaskAlreadyDoneException() {
        super("Task has already been mark as done!");
    }
}
