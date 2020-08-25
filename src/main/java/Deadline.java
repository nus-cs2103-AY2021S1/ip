package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;



public class Deadline extends Task {
    LocalDateTime  deadline;
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    String getDeadline() {
        return this.deadline.format(outputFormatter).toString();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getDeadline() +")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline task = (Deadline) o;
            return this.description.equals(task.description) && this.deadline.equals(task.deadline) && this.isDone == task.isDone;
        } else {
            return false;
        }
    }

    @Override
    public String saveFormat() {
        if (isDone) {
            return "D | 1 | " + this.getDescription() + " | " + this.deadline.format(inputFormatter).toString();
        } else {
            return "D | 0 | " + this.getDescription() + " | " + this.deadline.format(inputFormatter).toString();
        }
    }
}
