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
        return (isDone ? TICK : CROSS);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
