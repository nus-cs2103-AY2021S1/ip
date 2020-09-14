package botbot.tasks;

/**
 * Represents a to-do with a description and completion status.
 */
public class Todo extends Task {
    public static final char TYPE_CODE = 'T';

    /**
     * Creates a to-do.
     *
     * @param description Description of to-do.
     */
    public Todo(String description) {
        super(TYPE_CODE, description);
    }

    /**
     * Creates a to-do.
     *
     * @param description Description of to-do.
     * @param status Completion status of to-do.
     */
    public Todo(String description, TaskStatus status) {
        super(TYPE_CODE, description, status);
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s", getType(), getStatusIcon(), getDescription());
    }
}
