package duke.model;

/**
 * Represents a todo event.
 */
public class Todo extends Task {
    /**
     * Constructs a todo event.
     * @param description is the description of a todo event.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo event.
     * @param description is the description of a todo event.
     * @param isDone whether the task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
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
