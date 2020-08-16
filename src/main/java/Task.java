public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    protected String getStatusIcon() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "]"); //return tick or X symbols
    }

    public String taskRow() {
        return getStatusIcon() + " " + this;
    }

    @Override
    public String toString() {
        return description;
    }
}