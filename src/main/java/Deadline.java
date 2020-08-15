package main.java;

public class Deadline extends Task {
    String time;

    Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + time + ")";
    }
}
