package duke.task;

/**
 * Encapsulates a todo task.
 */
public class Todo extends Task {

    /**
     * Creates a deadline task with description, not done by default.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a deadline task with description, and done status.
     * @param description Description of the task.
     * @param isDone Done status of the task.
     */
    protected Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }
}
