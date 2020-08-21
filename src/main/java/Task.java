package main.java;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        String tick = "\u2713";
        String X = "\u2718";
        return (isDone ? tick : X); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String simplifiedTaskString() {
        return this.getTaskType() + " - " + (this.isDone ? "1" : "0") + " - " + this.getDescription();
    }

}