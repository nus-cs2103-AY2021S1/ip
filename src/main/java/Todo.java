/**
 * <h1>Represents a Todo task</h1>
 * Inherits from the Task class.
 */
public class Todo extends Task {

    public Todo(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
