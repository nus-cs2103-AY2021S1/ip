package main.java.dobby.task;

public class Task {
    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        String checkbox = "[";
        if (this.isDone()) {
            checkbox = checkbox + "\u2713] ";
        } else {
            checkbox = checkbox + "\u2718] ";
        }
        return checkbox + this.description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }
}
