/**
 * Represents a generic task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or cross mark depending on whether the task has been marked as completed.
     * @return tick or cross symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the format for permanent storage of a task in file.
     * @return string format for storing.
     */
    public String getStoringFormat() {
        if (this.isDone) {
            return "~ 1 ~ " + this.description;
        } else {
            return "~ 0 ~ " + this.description;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
