public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.isDone = false;
        this.description = desc;
    }

    public String getStatusIcon() {
        return (this.isDone
                ? "✓" // return tick
                : "✘");  // or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}