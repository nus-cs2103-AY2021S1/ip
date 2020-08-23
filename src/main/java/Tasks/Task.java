package tasks;

/**
 * Creates tasks objects.
 */
public class Task {

    /** The description for the task */
    protected String taskName;

    /** Boolean for whether or not the task is done */
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Gets the description for the task.
     * @return the description for the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets whether or not the task is done.
     * @return true if the task is done
     */
    public String getIsDone() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    /**
     * Updates the task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overriden toString method for task class
     * @return the string representation for task
     */
    @Override
    public String toString() {
        return this.getIsDone() + this.getTaskName();
    }
}
