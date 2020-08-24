public class Task {
    protected boolean isCompleted;
    protected String description;

    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }
    public void markDone() {
        this.isCompleted = true;
    }
    public String getStatusIcon() {
        return (isCompleted) ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
