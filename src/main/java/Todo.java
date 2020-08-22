/**
 * Represents a todo event.
 */
public class Todo extends Task {
    /**
     * Constructs a todo event.
     * @param description the description of the event.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
