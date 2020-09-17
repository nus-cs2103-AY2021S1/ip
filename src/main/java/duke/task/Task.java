package duke.task;

/**
 * Represent the task for the Duke program.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task class that can initialize value of isDone.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return the symbol of the status of this task.
     * @return ✓ if task is done, ✘ if task is not done
     */
    public String getStatusIcon() {
        return isDone
                ? "✓"
                : "✘";
    }

    /**
     * Return the data of this task.
     * @return data of this task as a String
     */
    public String getData() {
        return (this.isDone ? 1 : 0) + "/" + this.description;
    }

    /**
     * Return the status of this task.
     * @return true if this task is already done else false
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Return the description of this task.
     * @return description of this task as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the isDone attribute to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Return the string representation of this task.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
