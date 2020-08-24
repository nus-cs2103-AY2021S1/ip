package duke.task;

/**
 * Represents a task that needs to be done in the future. A
 * <code>Todo</code> object is represented as a Task.
 */
public class Todo extends Task{

    /**
     * Constructs a <code>Todo</code> object with a description.
     * This Todo is marked as undone.
     *
     * @param description Describes what to do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a <code>Todo</code> object with a description and
     * a boolean to indicate if the Todo is done.
     *
     * @param description Describes what to do.
     * @param isDone Indicates if the Todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a description of the Todo to be stored in a file.
     *
     * @return A description of the Todo.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    /**
     * Returns a description of the Todo.
     *
     * @return A description of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
