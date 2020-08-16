package main.java;

public class Deadline extends Task {
    String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getDeadline() +")";
    }
}
