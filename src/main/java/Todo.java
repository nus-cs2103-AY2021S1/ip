/**
 * Represents a todo task.
 */
public class Todo extends Task {

    private final String description;

    public Todo(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]" + "[" + this.getPriority() + "]" + " " + this.description;
    }
}