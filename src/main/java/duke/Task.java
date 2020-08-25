package duke;

/**
 * The task base class. Has a description and indicator whether the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     * 
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task constructor with specified isDone.
     * 
     * @param description the description of the task.
     * @param isDone      specify whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Get the status icon.
     *
     * @return return the icon according to the is done status.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Format task to be written to a file.
     *
     * @return formatted string of the task.
     */
    public String writeToFile() {
        return String.format("%b | %s", this.isDone, this.description);
    }
}
