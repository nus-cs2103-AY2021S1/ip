package duke.task;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;
    /** Indicates whether the task is marked as done. */
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     *
     * @param description is the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon which is either a tick or cross.
     *
     * @return a tick if the task is done or cross if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the boolean value of the task which is either true or false.
     *
     * @return true if the task is done or false if the task is not done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gives a string indicating whether a task is done and the description of the task.
     *
     * @param isFinished is the value 1 if task is done or 0 if task is not done.
     * @return a string indicating 1 or 0 and the description of the task.
     */
    public String save(int isFinished) {
        return isFinished + " | " + this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
