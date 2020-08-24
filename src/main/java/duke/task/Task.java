package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new Task object and set its isDone boolean
     *
     * @param description details about the Task
     * @param isDone whether Task is done or not
     * @return Task with a corresponding description and completed status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns either ✓ or ✘, depending on whether task has been done.
     *
     * @return ✓ or ✘.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns task description and its status icon.
     *
     * @return string containing its description and its status icon.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    public String infoString() {
        String x = "0";
        if (isDone) {
            x = "1";
        }
        return "T | " + x + " | " + this.description;
    }
}