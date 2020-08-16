public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        done = true;
    }

    public String displayString() {
        String doneString = done ? "✓" : "✗";
        return String.format("[%s] %s", doneString, description);
        // return String.format("[%s][%s] %s", taskTypeString(), doneString, description);
    }
}
