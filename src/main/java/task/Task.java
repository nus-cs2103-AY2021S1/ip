package task;

public abstract class Task {

    private String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "✓" : "X") + "] " + description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public abstract String getDescriptionForDatabase();
}
