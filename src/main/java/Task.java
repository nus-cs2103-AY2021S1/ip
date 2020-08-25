/**
 * Encapsulates a task with a description and a completion status.
 */
public class Task {

    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task using UTF-8 encoding.
     * Either a tick or a cross symbol.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the data from the task to be saved in duke.txt.
     *
     * @return String representing the data of the task.
     */
    public String getData() {
        int statusNumber = this.getStatusIcon().equals("\u2713")
                ? 1
                : 0;
        return statusNumber + " / " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
