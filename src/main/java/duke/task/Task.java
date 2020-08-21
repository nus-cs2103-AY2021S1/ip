package duke.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); // Return tick or X symbol
    }

    public void markAsDone() {
        isDone = true;
    }

    public TaskType getTaskType() {
        return type;
    }

    // Format: dd-MM-yyyy HH:mm
    public abstract String getTime();

    // Format: d MMM yyyy, hh:mm a
    public abstract String printTime();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, getStatusIcon(), description);
    }

}
