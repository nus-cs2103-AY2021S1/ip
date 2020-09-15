package duke.task;

/**
 * Todo is a simple subtype of Task which has a description.
 * Example:
 *     [T][✘] cry
 *
 * The task description has no further parsing and will be taken
 * literally as a string.
 */
public class Todo extends Task {

    /**
     * Constructs the todo.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super("T", description);
    }

    /**
     * Constructs the todo with its completion status.
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super("T", description, isDone);
    }
}
