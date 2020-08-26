package task;

/**
 * Encapsulates the details of a task to be done without time restriction.
 *
 * <p>The 'Task' supports operators, supported include: </p>
 *
 * <p> (i) Getters to details </p>
 */
public class ToDoTask extends task.Task {

    /**
     * Constructor to create this object.
     * @param description the description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Constructor to create this object.
     * @param description the description of the task.
     * @param isDone the completion status of the task.
     */
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the type of Tasks.
     * @return type of Task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * String representation of this object.
     * @return string representation of this object ([statusIcon] description).
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
