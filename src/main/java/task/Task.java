package task;

/**
 * Represents the Task that the user have to do.
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns the status of the Task.
     *
     * @return Status
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Mark the Task as completed.
     */
    public void completeTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (getStatus() ? "[\u2713] " : "[\u2717] ") + getTaskDescription();
    }
}
