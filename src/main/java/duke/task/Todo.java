package duke.task;

/**
 * Encapsulates a task to do.
 */
public class Todo extends Task {

    /**
     * Constant that determines the representation of this task's type.
     */
    protected static final String TASK_TYPE = "T";

    /**
     * Initialize a new To do object.
     *
     * @param description details of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialize a new to do object.
     *
     * @param description details of the task.
     * @param isDone whether the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the task in a save friendly format.
     * The way it is saved affects how the .txt file is read in {@link duke.Storage}
     *
     * @return Save-friendly string of the task.
     */
    @Override
    public String getSaveFormat() {
        return String.format("%s | %s", Todo.TASK_TYPE, super.getSaveFormat());
    }

    /**
     * Returns a string representation of the task.
     * Task is prepended by Todo.TASK_TYPE.
     *
     * @return Data of the task in string format.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.TASK_TYPE, super.toString());
    }
}
