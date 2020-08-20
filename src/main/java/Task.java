public class Task {
    public boolean isCompleted;
    public String description;

    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }
    public void markDone() {
        this.isCompleted = true;
    }
    public String getStatusIcon() {
        return (this.isCompleted) ? "\u2713" : "\u2718";
    }
    @Override
    public String toString() {
        String complete = (this.isCompleted) ? "\u2713" : "\u2718";
        return "[" + complete + "] " + description;
    }
}
