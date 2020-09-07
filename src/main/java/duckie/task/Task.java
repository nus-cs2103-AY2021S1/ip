package duckie.task;

/**
 * Parent class of all Tasks type.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiatess a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Checks the task and mark as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Checks if a Task is completed or not.
     *
     * @return Boolean value indicating the completeness of the task.
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Returns the description of a Task.
     *
     * @return String description of a Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type of a Task.
     *
     * @return null.
     */
    public String getType() {
        return null;
    }

    /**
     * Returns the date of a Task.
     *
     * @return null.
     */
    public String getDate() {
        return null;
    }

    /**
     * Overrides method to return the String representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        String tick = "\u2713";
        String cross = "\u2718";
        if (isDone) {
            return "[" + tick + "] " + this.description;
        } else {
            return "[" + cross + "] " + this.description;
        }
    }
}
