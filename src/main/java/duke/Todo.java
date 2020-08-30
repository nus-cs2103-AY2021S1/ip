package duke;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo class with description.
     * @param description Description of Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo class with description and status.
     * @param description Description of Todo task.
     * @param isDone Status of Todo task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
