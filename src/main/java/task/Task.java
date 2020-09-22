package task;

/**
 * Encapsulates the task into a class. The 'task.Task' class
 * supports getting status icon, getting description,
 * marking the task as done and converting the task
 * to a string.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected int priority;
    protected boolean hasPriority;

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
        assert isDone : "isDone should be true";
    }

    public void setPriority(int priority) {
        this.priority = priority;
        this.hasPriority = true;
    }

    public String toString() {
        if (hasPriority) {
            return "[" + getStatusIcon() + "] " + getDescription() + " priority " + priority;
        } else {
            return "[" + getStatusIcon() + "] " + getDescription();
        }
    }
}
