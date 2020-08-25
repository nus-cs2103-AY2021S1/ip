package duke.task;

/**
 * Represents a type of <code>Task</code>.
 */
public class ToDo extends Task {

    /**
     * Creates a todo with the specified description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
