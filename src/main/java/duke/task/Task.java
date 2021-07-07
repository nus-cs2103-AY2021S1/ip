package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Initialize the Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Status icon of the task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Description of the task.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Done status of the task.
     *
     * @return Done status.
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Type of task.
     *
     * @return Task type.
     */
    public String getType() {
        return type;
    }

    /**
     * Task information.
     *
     * @return Information.
     */
    public String toString() {
        return "["
                + getStatusIcon()
                + "]"
                + " "
                + getDescription();
    }

    /**
     * Task information in the format to be written to duke.txt.
     *
     * @return Information regarding the task.
     */
    public String toFileString() {
        return getDescription();
    }
}
