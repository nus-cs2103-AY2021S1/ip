public class Task {
    protected final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
    }

    String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    void markAsDone() {
        isDone = true;
    }
}