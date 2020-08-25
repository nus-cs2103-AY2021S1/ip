/**
 * Represents a Task object that contains the base behaviour of a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Retrieves a string representation of the task to be used for saving to the .txt file.
     *
     * @return Task in its save format
     */
    public String getSaveFormat() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }
}
