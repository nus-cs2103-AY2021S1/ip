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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
