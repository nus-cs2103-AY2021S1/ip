package main.java;

public class Task {
    private String task;
    private boolean done = false;

    public Task (String task) {
        this.task = task;
    }

    public void markAsDone() {
        done = true;
    }

    public String toString() {
        return done ? "[✓] " + task : "[✗] " + task;
    }
}
