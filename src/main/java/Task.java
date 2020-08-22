package main.java;

public abstract class Task {
    private String title;
    private boolean isDone;

    Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    void markAsDone() {
        if (this.isDone) {
            System.out.println("Already done.");
        } else {
            this.isDone = true;
            System.out.println("Marked as done: " + this);
        }
    }

    public String toString() {
        String status = this.isDone ? "✓" : "✗";
        return "[" + status + "] " + this.title;
    }

    public String print() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.title;
    }
}
