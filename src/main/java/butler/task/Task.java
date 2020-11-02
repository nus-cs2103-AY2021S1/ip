package butler.task;

/**
 * Represents a task.
 * <code>Task</code> has a summary, a completion status and type.
 * All tasks are initialised as incomplete by default.
 */
public abstract class Task {

    protected TaskType taskType;
    protected final String summary;
    protected boolean isComplete;

    /**
     * Constructs an incomplete task with the given <code>summary</code>.
     *
     * @param summary Summary of this task.
     */
    public Task(String summary) {
        this.summary = summary;
        this.isComplete = false;
        this.taskType = null;
    }

    /**
     * Gets the summary of this task.
     *
     * @return Summary of this task.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Marks this task as complete.
     */
    public void markComplete() {
        isComplete = true;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return (isComplete ? "[Y]" : "[N]") + " " + summary;
    }

    /**
     * Returns a string representation of this task for storage in hard disk.
     *
     * @return String representation of this task for storage in hard disk.
     */
    public String toStorageString() {
        return (isComplete ? "complete" : "incomplete") + " "
                + taskType.toString() + " " + summary;
    }

    /**
     * Returns a deep copy of this task.
     *
     * @return A deep copy of this task.
     */
    public abstract Task copy();
}
