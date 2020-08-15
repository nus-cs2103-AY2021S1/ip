package main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
