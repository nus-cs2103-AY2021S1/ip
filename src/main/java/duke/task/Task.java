package main.java.duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void editDescription(String newDesc) {
        this.description = newDesc;
    }

    public String getStatusIcon() {
        return (isDone ? ":)" : ":("); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveToFile() {
        return isDone ? "1/" : "0/" + description;
    }
}
