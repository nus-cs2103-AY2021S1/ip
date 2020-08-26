/**
 * Represents a to do task.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
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