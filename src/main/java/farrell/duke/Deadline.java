package main.java.farrell.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Encapsulates data for a deadline task.
 */
public class Deadline extends Task {
    /** The deadline for the task */
    private LocalDate time;

    Deadline(String description, LocalDate time) {
        this(description, false, time);
    }

    Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        taskType = TaskType.DEADLINE;
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String formattedTime = time.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
