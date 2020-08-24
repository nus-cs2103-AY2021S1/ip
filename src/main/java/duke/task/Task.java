package duke.task;

/**
 * Parent class for the various types of tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for creating Task object
     *
     * @param description name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves status icon (tick or cross)
     *
     * @return status icon
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks completed task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * toString method for Task
     *
     * @return task in string form
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
