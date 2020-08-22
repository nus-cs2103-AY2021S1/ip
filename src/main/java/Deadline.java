package main.java;

public class Deadline extends Task {
    String time;

    Deadline(String description, String time) {
        this(description, false, time);
    }

    Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        taskType = TaskType.DEADLINE;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
