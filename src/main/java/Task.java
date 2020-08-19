public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    public void markDone() {
        isDone = true;
    }

    public String toString() {
        return getStatusIcon() + description;
    }
}
