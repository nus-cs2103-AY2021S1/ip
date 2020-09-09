package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean hasTag;

    /**
     * Creates a Task.
     *
     * @param description description of task
     */
    public Task(String description, boolean hasTag) {
        this.description = description;
        this.isDone = false;
        this.hasTag = hasTag;
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
