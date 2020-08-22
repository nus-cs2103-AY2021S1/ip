package main.java;

import java.time.LocalDate;

public abstract class Task {
    private String title;
    private boolean isDone;

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

    abstract LocalDate getDate();
}
