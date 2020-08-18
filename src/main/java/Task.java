package main.java;

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "\u2713" : "\u2718";

        return statusIcon + " " + description;
    }
}
