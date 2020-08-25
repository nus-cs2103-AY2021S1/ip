package duke.task;

import java.time.LocalDate;

public class Task {
    protected static int numOfTasks;
    protected String description;
    protected boolean isCompleted;
    protected final int id;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
        this.id = numOfTasks++;
    }

    Task(String description, String completionStatus) {
        this.description = description;
        this.isCompleted = completionStatus.equals("1");
        this.id = numOfTasks++;
    }

    String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718");
    }

    String getType() {
        return null;
    }

    int getId() {
        return id;
    }

    LocalDate getDate() {
        return null;
    }

    void markAsDone() {
        isCompleted = true;
    }

    public String encode() {
        return (getId() + " | " + getType() + " | " + (isCompleted ? "1" : "0") + " | " + description + (getDate() != null ? (" | " + getDate()) : ""));
    }

    public boolean includesKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
