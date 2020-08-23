package duke.task;

public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toFileString() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
