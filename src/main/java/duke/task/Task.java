package duke.task;

public abstract class Task {
    public String description;
    public boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
    }

    public void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
