/**
 * Encapsulates the task into a class. The 'Task' class
 * supports getting status icon, getting description,
 * marking the task as done and converting the task
 * to a string.
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

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
