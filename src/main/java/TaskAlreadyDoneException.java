public class TaskAlreadyDoneException extends DukeException {
    protected TaskAlreadyDoneException() {
        super("Task has already been mark as done!");
    }
}
