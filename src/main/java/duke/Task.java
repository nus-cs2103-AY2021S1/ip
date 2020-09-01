package duke;

/**
 * Represents a Task with a specified description, and boolean to indicate if completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class
     */
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
        return (isDone ? "O" : "X");
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
        String done = this.isDone ? "O" : "X";
        return "[" + done + "]" + " " + this.description;
    }
}
