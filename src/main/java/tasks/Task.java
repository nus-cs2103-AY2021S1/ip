package main.java.tasks;

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
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String databaseString() {
        String doneStatus = this.isDone ? "true" : "false";
        return  doneStatus + " | " + this.description;
    }
}
