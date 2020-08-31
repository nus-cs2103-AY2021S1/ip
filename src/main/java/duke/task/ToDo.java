package duke.task;

/**
 * ToDo task, which is one of the three Task object.
 */
public class ToDo extends Task {
    /**
     * Initializes the ToDo task with isDone boolean value.
     *
     * @param description Description of the task.
     * @param isDone Boolean value to represent if task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO, "-");
    }

    /**
     * Initializes the ToDo task with isDone boolean value.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description, false, TaskType.TODO, "-");
    }

    /**
     * Provides a string representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
