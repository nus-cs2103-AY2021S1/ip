package duke.task;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = sanitizeString(description);
        done = isDone;
    }

    public boolean isDone() {
        return done;
    }

    protected void markAsDone() {
        done = true;
    }

    public String displayString() {
        String doneString = done ? "✓" : "✗";
        return String.format("[%s][%s] %s", taskTypeString(), doneString, description);
    }

    protected String taskTypeString() {
        return "T";
    }

    public String getDescription() {
        return description;
    }

    protected static String sanitizeString(String s) {
        return s.replaceAll("\t", "    ");
    }
}
