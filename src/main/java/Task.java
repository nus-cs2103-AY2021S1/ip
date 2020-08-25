package main.java;

public class Task {
    private String task;
    private String type = "Task";
    private boolean done = false;
    private String fullText;

    public Task (String task, String fullText) {
        this.task = task;
        this.fullText = fullText;
    }

    protected String getType() {
        return type;
    }

    protected String getFullText() {
        return fullText;
    }

    protected void markAsDone() {
        done = true;
    }

    public String toString() {
        return done ? "[✓] " + task : "[✗] " + task;
    }
}
