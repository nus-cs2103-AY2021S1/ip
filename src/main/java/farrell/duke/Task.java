package main.java.farrell.duke;

public class Task {
    TaskType taskType;
    String description;
    boolean isDone;

    Task(String description) {
        this(description, false);
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
