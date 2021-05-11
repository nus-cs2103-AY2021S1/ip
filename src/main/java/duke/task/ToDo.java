package duke.task;

/**
 * Defines a todo
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName, TaskType.T);
    }
    public ToDo(String taskName, Priority priority) {
        super(taskName, TaskType.T, priority);
    }
}
