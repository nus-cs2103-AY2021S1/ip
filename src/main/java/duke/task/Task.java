package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean hasTag = false;
    protected Tag tag;

    /**
     * Creates a Task.
     *
     * @param description description of task
     */
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        if (!tag.isBlank()) {
            this.hasTag = true;
        }
        this.tag = new Tag(tag);
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return this.description;
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
