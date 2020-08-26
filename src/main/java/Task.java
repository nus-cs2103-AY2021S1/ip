package main.java;

public class Task {
    private String task;
    private boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void doTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String check = isDone ? "✓" : "✗";
        return String.format("[%s] %s", check, task);

    }

    public String saveToString() {
        int check = isDone ? 1 : 0;
        return String.format("%d | %s", check, task);
    }
}
