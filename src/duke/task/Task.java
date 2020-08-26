package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    // Markers
    public static final String TICK = "[" + "\u2713" + "]";
    private static final String CROSS = "[" + "\u2718" + "]";

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
    
    public boolean hasDoneStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
