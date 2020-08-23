package duke.task;

public abstract class Task implements Serialisable {
    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "✓" : "✘");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public String serialise() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

}
