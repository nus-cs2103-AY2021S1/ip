package main.java.farrell.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Encapsulates data for a deadline task.
 */
public class Deadline extends TimedTask {
    Deadline(String description, LocalDate time) {
        this(description, false, time);
    }

    Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
        taskType = TaskType.DEADLINE;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
