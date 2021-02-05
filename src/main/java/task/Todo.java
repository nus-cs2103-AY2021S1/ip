package task;

/**
 * <h1>Represents a Todo task</h1>
 * Inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object.
     *
     * @param task Todo task details.
     * @param isCompleted True if todo task is completed, false otherwise.
     * @param priority Priority level of the todo task, if any.
     */
    public Todo(String task, boolean isCompleted, int priority) {
        super(task, isCompleted, priority);
    }

    @Override
    public String toString() {
        String text = "[T]" + super.toString();
        if (getPriority() != 0) {
            return text + " (priority: " + getPriority() + ")";
        } else {
            return text;
        }
    }
}
