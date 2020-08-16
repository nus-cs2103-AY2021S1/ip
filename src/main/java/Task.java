package main.java;

public abstract class Task {
    String title;
    boolean isDone;

    Task(String title) {
        this.title = title;
        this.isDone = false;
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
}
