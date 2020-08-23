package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;
    public Event (String description, String period) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(period.substring(0,15), formatter);
        this.end = LocalDateTime.parse(period.substring(19), formatter);
    }

    String getPeriod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return this.start.format(formatter).toString() + " to " + this.end.format(formatter).toString();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (at: " + getPeriod() +")";
    }
}

