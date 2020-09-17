package duke.task;

/**
 * Creates a task.
 */
public class Task {

    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates the message to be written into the local file.
     * 
     * @return A string representation of the task in the local file.
     */
    public String writeMessage() {
        return "";
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "✓" : "✗") + "] " + this.description;
    }
}
