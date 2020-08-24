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
    public String getFileStatusIcon() { return (isCompleted) ? "1" : "0"; }

    public String fileString() {
        return getFileStatusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
