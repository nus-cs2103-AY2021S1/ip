/**
 * Represents a to do task.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class Todo extends Task {
    /**
     * A basic Todo constructor.
     *
     * @param description The description of the task.
     */
    public Todo(final String description) {
        super(description);
    }

    /**
     * Todo constructor that creates a todo task that is marked as done.
     *
     * @param description The description of the task.
     * @param isDone      The task is marked as done.
     */
    public Todo(final String description, final boolean isDone) {
        super(description, isDone);
    }

    /**
     * Formats the task for display to the user.
     * @return This returns a string containing the task details.
     */
    public String display() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
