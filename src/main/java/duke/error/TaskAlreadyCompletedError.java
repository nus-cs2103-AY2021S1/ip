package duke.error;

import duke.task.Task;

public class TaskAlreadyCompletedError extends DukeError {
    public TaskAlreadyCompletedError(Task task) {
        super(String.format("Sorry! You have already completed '%s'.", task.getDescription()));
    }
}
