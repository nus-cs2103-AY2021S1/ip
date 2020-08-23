public class Task {
    protected final String description;
    protected final String type;
    protected final boolean isDone;
    protected final String timestamp;

    protected Task(String description, String type, boolean isDone, String timestamp) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return isDone ? "1" : "0";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markDone() {
        return new Task(this.description, this.type, true, this.timestamp);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + getStatusIcon() + "]" +
                " " + this.description;
    }
}
