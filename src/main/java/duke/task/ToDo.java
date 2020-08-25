package duke.task;

/**
 * ToDo task, which is one of the three Task object.
 */
public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO, "-");
    }

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
