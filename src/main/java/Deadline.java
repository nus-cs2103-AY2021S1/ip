package main.java;

public class Deadline extends Task {
    String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getDeadline() +")";
    }

    @Override
    public String saveFormat() {
        if (isDone) {
            return "D | 1 | " + this.getDescription() + " | " + this.getDeadline();
        } else {
            return "D | 0 | " + this.getDescription() + " | " + this.getDeadline();
        }
    }
}
