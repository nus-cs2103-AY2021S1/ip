package duke.task;

/**
 * Represents tasks without any date attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo with the specified description.
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the short form of this todo.
     * @return The short form of this todo.
     */
    @Override
    public String getShortForm() {
        return "T";
    }

    /**
     * Returns a string representation of this todo.
     * @return A string representation of this todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
