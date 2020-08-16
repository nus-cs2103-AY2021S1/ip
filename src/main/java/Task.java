public class Task {
    protected String description;
    protected boolean isDone;

    // Markers
    private final String TICK = "[" + "\u2713" + "]";
    private final String CROSS = "[" + "\u2718" + "]";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return TICK + " " + this.description;
        } else {
            return CROSS + " " + this.description;
        }
    }
}
