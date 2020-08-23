package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    public Event (String description, String period) {
        super(description);
        this.start = LocalDateTime.parse(period.substring(0,15), inputFormatter);
        this.end = LocalDateTime.parse(period.substring(19), inputFormatter);
    }

    public Event(boolean isDone, String description, String period) {
        super(isDone, description);
        this.start = LocalDateTime.parse(period.substring(0,15), inputFormatter);
        this.end = LocalDateTime.parse(period.substring(19), inputFormatter);
    }

    String getPeriod() {
        return this.start.format(outputFormatter).toString() + " to " + this.end.format(outputFormatter).toString();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (at: " + getPeriod() +")";
    }

    @Override
    public String saveFormat() {
        if (isDone) {
            return "E | 1 | " + this.getDescription() + " | " + this.start.format(inputFormatter) + " to " + this.end.format(inputFormatter);
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.start.format(inputFormatter) + " to " + this.end.format(inputFormatter);
        }
    }
}

