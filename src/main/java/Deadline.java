package main.java;

public class Deadline extends Task {
    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
       return super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String simplifiedTaskString() {
        return super.simplifiedTaskString() + " - " + this.deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
