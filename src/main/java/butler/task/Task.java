package butler.task;

/**
 * Represents a task.
 * <code>Task</code> has a summary, a completion status and type.
 * All tasks are initialised as incomplete by default.
 */
public abstract class Task {

    protected TaskType taskType;
    private String summary;
    private boolean isComplete;

    /**
     * Constructs an incomplete task with the given <code>summmary</code>.
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
     * Gets the boolean completion status of this task.
     *
     * @return <code>true</code> if this task is complete else <code>false</code>
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Gets the task type of this task.
     *
     * @return Task type.
     */
    public TaskType getTaskType() {
        return taskType;
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
        return (isComplete ? "[Y]" : "[N]") + " " + getSummary();
    }
}
