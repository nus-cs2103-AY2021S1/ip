/**
 * Class representing generic tasks.
 */
public class Task {
    /** Variable to store task description. */
    protected String description;
    /** Variable to store if the task is completed. */
    protected boolean isDone;

    /**
     * Constructor used to create tasks.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon, depending on whether task is done.
     *
     * @return String representing status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets task done status.
     *
     * @param done Task done status.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
