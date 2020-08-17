public class Task {
    protected String details;
    protected boolean isCompleted;

    public Task(String details) {
        this.details = details;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "\u2713" : "\u2718") + "] " + details;
    }
}
