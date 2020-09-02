package duke.task;

import duke.task.Task;
import duke.task.TaskType;

/**
 * Defines a todo
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName, TaskType.T);
    }
}
