package butler.task;

/**
 * Represents a task to be done.
 * This task has a summary.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a task with the given <code>summary</code>.
     *
     * @param summary Summary of this task.
     */
    public ToDoTask(String summary) {
        super(summary);
        this.taskType = TaskType.TODO;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of this task for storage in hard disk.
     *
     * @return String representation of this task for storage in hard disk.
     */
    @Override
    public String toStorageString() {
        return super.toStorageString();
    }

    /**
     * Returns a deep copy of this task.
     *
     * @return A deep copy of this task.
     */
    @Override
    public Task copy() {
        ToDoTask task = new ToDoTask(summary);
        task.isComplete = this.isComplete;
        return task;
    }
}
