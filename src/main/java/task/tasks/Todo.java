package task.tasks;

/**
 * Task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Creates a todo.
     *
     * @param description Contents of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of a todo description and its completion status.
     *
     * @return String representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}