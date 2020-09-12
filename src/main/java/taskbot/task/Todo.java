package taskbot.task;

/**
 * Encapsulates a todo task.
 */
public class Todo extends Task {
    /**
     * Creates an incomplete Todo task.
     *
     * @param task Description of task
     */
    public Todo(String task) {
        super(task);
    }
    /**
     * Creates a Todo task based on its completeness.
     *
     * @param task Description of task
     * @param isDone Whether the task is done
     */
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Todo: " + super.getTask() + "\n";
    }
}
