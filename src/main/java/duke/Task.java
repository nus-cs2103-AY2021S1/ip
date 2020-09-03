package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    public static final int DONE = 1;
    public static final int NOT_DONE = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
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
        int doneInt = this.isDone ? DONE : NOT_DONE;
        return String.format("%s | %d | %s", type, doneInt, this.description);
    }
}