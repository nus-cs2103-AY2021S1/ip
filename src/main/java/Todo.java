/**
 * Represents a Todo that the user has.
 */
public class Todo extends Task {

    /**
     * Initializes the Todo with the description.
     * @param description The description of this todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes the Todo with the description, and whether it is done.
     * @param description The description of this todo.
     * @param isDone Whether this todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
