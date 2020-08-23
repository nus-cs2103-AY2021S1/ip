/**
 * Todo is a subtype of Task which has a description
 * of the task to be done. This task is rather similar
 * to the parent Task class, but is necessary to have it
 * be on the same level as the other subtypes of Task.
 */

public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super("T", description);
    }

    public Todo(String description, boolean isDone) {
        super("T", description, isDone);
    }
}
