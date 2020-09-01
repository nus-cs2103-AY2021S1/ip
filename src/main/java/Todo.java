/**
 * Represents a to do task.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Todo extends Task {
    /**
     * To do constructor.
     * @param description The description of the task.
     */
    public Todo(final String description) {
        super(description);
    }

    /**
     * To do constructor, marked as done.
     * @param description The description of the task.
     * @param isDone      The task is marked as done.
     */
    public Todo(final String description, final boolean isDone) {
        super(description, isDone);
    }

    /**
     * This method formats the task for display to the user.
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
