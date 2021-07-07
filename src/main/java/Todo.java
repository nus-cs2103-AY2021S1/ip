/**
 * Represents a Todo task object that has a description.
 */
public class Todo extends Task {
    public Todo(String description, Priority priority) {
        super(description, priority);
    }

    public Todo(String description, Priority priority, boolean isDone) {
        super(description, priority, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getSaveFormat() {
        return "T | " + super.getSaveFormat();
    }
}
