package main.java;

public class Deadline extends Task {
    public Deadline(String description, String deadline) {
        super(description + " (by: " + deadline + ")");
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
