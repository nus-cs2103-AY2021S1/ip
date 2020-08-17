public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
