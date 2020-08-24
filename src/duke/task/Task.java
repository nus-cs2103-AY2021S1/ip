package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]");
    }

    public abstract String getType();

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() { return isDone; }

    public abstract Task markAsDone();

    @Override
    public abstract String toString();
}
