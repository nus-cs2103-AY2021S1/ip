package main.java;

public class Deadline extends Task {
    String deadline;

    Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
