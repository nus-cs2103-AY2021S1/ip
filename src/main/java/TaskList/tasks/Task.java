package main.java.TaskList.tasks;

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

    public String getFullText() {
        return fullText;
    }

    public void markAsDone() {
        done = true;
    }

    public String toString() {
        return done ? "[✓] " + task : "[✗] " + task;
    }
}
