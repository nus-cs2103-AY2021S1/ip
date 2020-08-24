package main.java.farrell.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Encapsulates data for an event task
 */
public class Event extends Task {
    /** The time when the event is occurring */
    LocalDate time;

    Event(String description, LocalDate time) {
        this(description, false, time);
    }

    Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        taskType = TaskType.EVENT;
        this.time = time;
    }

    @Override
    public String toString() {
        String formattedTime = time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        return "[E]" + super.toString() + " (at: " + formattedTime + ")";
    }
}
