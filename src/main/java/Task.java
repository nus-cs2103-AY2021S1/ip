public class Task {
    protected String description;
    protected String identifier;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}
