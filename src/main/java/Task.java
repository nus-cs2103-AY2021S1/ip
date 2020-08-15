public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s] %s", statusIcon, description);
    }
}
