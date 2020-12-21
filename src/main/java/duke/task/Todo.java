package duke.task;

/**
 * Represents a task with a description of what to do.
 */
public class Todo extends Task {
    /**
     * Creates a new task with the specified description of what to do.
     *
     * @param description Description of what to do.
     */
    public Todo(String description) {
        super(description, "[T]");
    }

    /**
     * Returns a String representation of a todo task.
     *
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
