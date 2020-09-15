package duke.tasks;

/**
 * Represents a Task that requires completion.
 */
public class ToDo extends Task {

    private static final String taskType = "ToDo";

    /**
     * @param description Todo description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of this ToDo to be displayed to the user.
     *
     * @return The String representation of this ToDo to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + super.toString();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.ToDo;
    }
}
