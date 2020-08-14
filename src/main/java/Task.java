public class Task {

    protected boolean isCompleted;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.isCompleted = completed;
    }

    public Task markCompleted() {
        return new Task(description, true);
    }

    @Override
    public String toString() {
        String completionStatus = isCompleted ? "\u2713" : "\u2718";
        return String.format("[%s] %s", completionStatus, description);
    }
}
