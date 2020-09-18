/**
 * Represents a to-do object.
 */
public class Todo extends Task {

    /**
     * Creates a to-do object.
     *
     * @param description to-do description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a to-do object.
     *
     * @param description to-do description
     * @param isDone whether to-do is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
