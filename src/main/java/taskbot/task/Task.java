package taskbot.task;

/**
 * This class encapsulates the functionality of a task.
 */
public abstract class Task {
    private String task;
    private boolean isDone;

    /**
     * Creates an incomplete task.
     *
     * @param task Description of task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Creates a Task given its completeness.
     *
     * @param task Description of task.
     * @param isDone Whether the task is complete.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Marks this task as complete.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Gets the task description.
     *
     * @return The task to be completed.
     */
    public String getTask() {
        return task;
    }

    /**
     * Gets the icon signifying completeness.
     *
     * @return The icon representing a complete task or not.
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "✓" : "✗");
    }

    /**
     * Gets whether the task is complete.
     *
     * @return True if complete, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTask();
    }
}
