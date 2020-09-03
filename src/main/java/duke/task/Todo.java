package duke.task;

/**
 * Represents tasks without any date attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo with the specified description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of this todo.
     *
     * @return String representation of this todo.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
