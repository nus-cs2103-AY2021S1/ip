package duke;

/**
 * Represents a Task with a specified description, and boolean to indicate if completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick if task is completed, a cross if otherwise.
     *
     * @return Tick if completed, cross if not.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    /**
     * Sets the task to be completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    public String recordString() {
        return "";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
