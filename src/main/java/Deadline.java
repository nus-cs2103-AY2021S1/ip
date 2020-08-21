package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy, HH:mm");
        return super.toString() + " (by: " + deadline.format(formatter) + ")";
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