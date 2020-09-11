package duke.task;

import duke.Priority;

/**
 * Represents a todo as a task.
 */
public class Todo extends Task {

    /**
     * Creates a todo from a description.
     *
     * @param priority Priority of the todo.
     * @param description Description of the todo.
     */
    public Todo(Priority priority, String description) {
        super(priority, description);
    }

    /**
     * Creates a todo from a description and completion status.
     *
     * @param priority Priority of the todo.
     * @param description Description of the todo.
     * @param isDone Completion status of the todo.
     */
    public Todo(Priority priority, String description, boolean isDone) {
        super(priority, description, isDone);
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
