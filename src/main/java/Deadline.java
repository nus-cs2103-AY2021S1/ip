package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;



public class Deadline extends Task {
    LocalDateTime  deadline;
    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return this.deadline.format(formatter).toString();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getDeadline() +")";
    }
}
