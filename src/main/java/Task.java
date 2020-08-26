public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String saveAs() { return isDone + " | " + description; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}