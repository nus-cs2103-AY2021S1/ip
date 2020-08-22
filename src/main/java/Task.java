public abstract class Task {
    private final String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected boolean checkIfDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}