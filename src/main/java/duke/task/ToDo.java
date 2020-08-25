package duke.task;

/**
 * Represents a todo task
 */
public class ToDo extends Task {
    /**
     * Create a <code>ToDo</code> object
     * @param description The description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Represents the type of this task through an icon.
     * @return An icon
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * Converts the task to string.
     * @return The string representation of this task
     */
    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description;
    }
}
