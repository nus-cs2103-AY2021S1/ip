package task;

/**
 * Represents the Task that the user have to do.
 */
public class Task {
    protected String taskDescription;
    protected boolean done;

    public Task(String taskDescription, boolean done) {
        this.taskDescription = taskDescription;
        this.done = done;
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
    public boolean getDone() {
        return this.done;
    }

    /**
     * Mark the Task as completed.
     */
    public void completeTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (getDone() ? "[\u2713] " : "[\u2717] ") + getTaskDescription();
    }
}
