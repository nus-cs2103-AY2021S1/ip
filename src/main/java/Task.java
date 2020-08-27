public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? " ✓" : "✘"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String toText() {
        return toText("T");
    }

    public String toText(String type) {
        int doneInt = this.isDone ? 1 : 0;
        return String.format("%s | %d | %s", type, doneInt, this.description);
    }
}