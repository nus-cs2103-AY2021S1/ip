public class Task {
    protected String details;
    protected boolean isCompleted;

    public Task(String details) {
        this.details = details;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getDetails() {
        return this.details;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "✓" : "✗") + "] " + details;
    }
}
