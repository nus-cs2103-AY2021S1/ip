package main.java;

public class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(boolean done) {
        isDone = done;
    }

    public String getStatusString() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusString() + "]" + " " + description;
    }
}
