package main.java;

public class Task {
    private String task;
    private boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        String check = done ? "✓" : "✗";
        return String.format("[%s] %s", check, task);

    }
}
