package duke.task;

/**
 * The Todo, a task without any date or time attached to it.
 */
public class Todo extends Task {
    /**
     * Instantiates a new Todo.
     *
     * @param description the description.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Instantiates a new Todo.
     *
     * @param description the description.
     * @param isDone      true if todo is done, false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }
}
