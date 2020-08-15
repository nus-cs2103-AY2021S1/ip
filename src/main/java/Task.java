public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        if (!isDone) {
            return new Task(description, true);
        } else {
            return this;
        }
    }

    public String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
