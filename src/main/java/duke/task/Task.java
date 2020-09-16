package duke.task;

/**
 * This class represent tasks
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor with the addition of isDone.
     * @param isDone Check if the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getIsDone() { return isDone; }

    public String getDescription() {
        return this.description;
    }

    /**
     * Mark the task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Audience-friendly icon that represent if the task is done.
     */
    public String getIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    /**
     * Represent the task in an audience-friendly form.
     * To be overwritten by child class.
     */
    public String getStatus() {
        return "";
    }

    /**
     * Represent the task in a suitable form to store data.
     * To be overwritten by child class.
     */
    public String getDataFormat() {
        return "";
    }
    //...
}