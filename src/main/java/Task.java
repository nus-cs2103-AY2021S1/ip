/**
 * Represents a task.
 */
public class Task {

    private final String description;
    public boolean isDone;
    private int priority = 3; //set to lowest priority

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.priority + this.description;
    }
}
