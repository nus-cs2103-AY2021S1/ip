package duke;

/**
 * Todo is a type of task with a description.
 */
public class Todo extends Task {

    /**
     * Constructor of todo.
     *
     * @param description description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of the object.
     *
     * @return string string representation of the object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return String.format("%s || todo || %s", super.toSaveString(), this.description);
    }
}
