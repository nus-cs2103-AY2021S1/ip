/**
 * Encapsulates a task with a description and a completion status.
 */
public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     */
    public void markTaskAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
