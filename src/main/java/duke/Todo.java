package duke;

/**
 * Represents a Todo, a type of Task, in the Duke program.
 */
public class Todo extends Task {
    /**
     * Creates a Todo
     * @param description the description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
