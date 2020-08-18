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
        char statusIcon = isDone ? UIPrint.tick : UIPrint.cross;

        return statusIcon + " " + description;
    }
}
