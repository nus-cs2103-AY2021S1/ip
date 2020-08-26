package duke;

/**
 * Represents a Parser that parses user input.
 */
public class Task {
    protected boolean isDone;
    protected String description;
    
    /**
     * Creates an instance of a Task.
     * @param description Contents of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a tick or a cross when the task is done or undone respectively.
     * @return A tick or a cross.
     */
    public String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718";
    }

    /**
     * Returns a tick or a cross when the task is done or undone respectively.
     * @return A tick or a cross.
     */
    public boolean isDone() {
        return this.isDone;
    }
    
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns String representation of a task.
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
