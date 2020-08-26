package duke.task;

/**
 * Class representing a todo.
 */
public class ToDo extends Task {
    /**
     * Creates a brand new ToDo.
     * @param description Description of the todo.
     */
    public ToDo(String description) {
        super(description);
        taskType = "T";
    }

    /**
     * Creates a ToDo from existing data.
     * @param uniqueId Unique Id of the todo.
     * @param isDone todo completion status.
     * @param description Description of the todo.
     */
    public ToDo(String uniqueId, boolean isDone, String description) {
        super(uniqueId, isDone, description);
        taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}