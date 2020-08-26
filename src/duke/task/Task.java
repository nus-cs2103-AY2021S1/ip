package duke.task;

public class Task {
    // Indicators
    public static final String STATUS_TICK = "[" + "\u2713" + "]";
    public static final String STATUS_CROSS = "[" + "\u2718" + "]";

    private String description;
    private boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? STATUS_TICK : STATUS_CROSS);
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
