package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    Task(String description, String completionStatus) {
        this.description = description;
        this.isCompleted = completionStatus.equals("1");
    }

    String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718");
    }

    String getType() {
        return null;
    }

    LocalDate getDate() {
        return null;
    }

    void markAsDone() {
        isCompleted = true;
    }

    public String encode() {
        return (getType() + " | " + (isCompleted ? "1" : "0") + " | " + description + (getDate() != null ? (" | " + getDate()) : ""));
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
