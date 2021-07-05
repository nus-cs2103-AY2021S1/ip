package task;

/**
 * Inherits from task and represents a basic to-do task.
 */
public class ToDoTask extends Task {
    /**
     * Creates a to-do task.
     *
     * @param description Description of the task.
     * @param isDone      State of whether the task is done.
     */
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Return a string representation of the to-do task.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
