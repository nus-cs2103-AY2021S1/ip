package task;

/**
 * <h1>Represents a Todo task</h1>
 * Inherits from the Task class.
 */
public class Todo extends Task {

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
