package main.java.farrell.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TimedTask extends Task {
    /** The time when the event is occurring */
    protected LocalDate time;

    TimedTask(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String formattedTime = time.format(formatter);
        return super.toString() + " (at: " + formattedTime + ")";
    }
}
