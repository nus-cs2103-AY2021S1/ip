package gel.task;

/**
 * Represents task as a String and if the task is done.
 */
public class Task {
    protected String description;
    protected boolean done;

    /**
     * Constructor for <code>Task</code> class.
     *
     * @param description What the task is.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (done ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public void markAsDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
            return this.getStatusIcon() + " "
                    + this.getDescription();
    }
}