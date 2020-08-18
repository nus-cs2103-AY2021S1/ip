package service;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = (!this.isDone ? "[x]. ": "[v]. ");
        return status + " " + this.description;
    }
}
