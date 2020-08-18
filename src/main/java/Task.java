public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true; // TODO: exception handling + checking if task is already done.
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
