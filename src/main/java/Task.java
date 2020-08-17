package main.java;

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String icon = isDone ? "\u2713" : "\u2718";
        return "[" + icon + "] " + name;
    }
}
