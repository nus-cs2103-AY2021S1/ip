package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task.
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toText() {
        return "";
    }
    public String toString() {
        return this.description;
    }
}
