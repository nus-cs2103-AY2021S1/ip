package main.java.duke;

public class Task {

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
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return "General_Task";
    }

    public String isDone() {
        return isDone ? "1" : "0";
    }

    public String[] getInfo() {
        return new String[]{this.getType(), this.isDone(), this.description, ""};
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}