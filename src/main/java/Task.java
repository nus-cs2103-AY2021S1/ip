public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public Task markAsDone() {
        return new Task(description, true);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
