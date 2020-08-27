package duke.task;

/**
 * Represents a todo as a task.
 */
public class Todo extends Task {

    /**
     * Creates a todo from a description.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo from a description and completion status.
     *
     * @param description Description of the todo.
     * @param isDone Completion status of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getData() {
        return "T / " + super.getData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
