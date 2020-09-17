package duke;

/**
 * Represents a To-do. A type of Task.
 */
public class Todo extends Task {
    /**
     * Creates a To-do object with the specified description.
     * @param description Description of the To-do.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    private Todo(String desc, boolean isDone) {
        super(desc, isDone);
        this.type = "T";
    }

    @Override
    public Todo taskDone() {
        return new Todo(this.getDescription(), true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
